package com.lyh.dietary_assistant.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyh.dietary_assistant.mapper.DietDiaryMapper;
import com.lyh.dietary_assistant.mapper.FoodMapper;
import com.lyh.dietary_assistant.pojo.DietDiary;
import com.lyh.dietary_assistant.pojo.Food;
import com.lyh.dietary_assistant.pojo.FoodExample;
import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.service.FoodService;
import com.lyh.dietary_assistant.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private DietDiaryMapper dietDiaryMapper;

    @Autowired
    private ObjectConverter objectConverter;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Food> getMyFood(HttpServletRequest req) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 透過用戶 id 查詢所有 Food
        FoodExample foodExample = new FoodExample();
        foodExample.createCriteria().andOwnerEqualTo(id);
        List<Food> foods = foodMapper.selectByExample(foodExample);

        return foods;
    }

    @Override
    public List<Food> putMyFood(HttpServletRequest req, Map<String, String> allParams) {
        Integer id = parseInt(allParams.get("id"));
        Integer owner = parseInt(allParams.get("owner"));
        String foodname = allParams.get("foodName");
        Float calories = parseFloat(allParams.get("calories"));
        Float carbohydrate = parseFloat(allParams.get("carbohydrate"));
        Float fat = parseFloat(allParams.get("fat"));
        Float protein = parseFloat(allParams.get("protein"));
        Short portionsize = parseShort(allParams.get("portionSize"));

        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 透過 User id 和 Food id 來更新 Food
        FoodExample foodExample = new FoodExample();
        foodExample.createCriteria().andIdEqualTo(id).andOwnerEqualTo(userId);
        foodMapper.updateByExampleSelective(new Food(null, null, foodname, calories, carbohydrate, fat, protein, portionsize), foodExample);

        // 查詢更新過後的 Food
        return getMyFood(req);
    }

    @Override
    public void postMyFood(HttpServletRequest req, Map<String, Object> jsonData) throws ParseException {
        DietDiary dietDiary = objectConverter.convertToDietDiary(jsonData);

        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 檢查該用戶的食品列表中是否已經有該食品，若沒有則先添加，有則直接添加飲食日記。
        String foodname = dietDiary.getMyFood().getFoodname();
        FoodExample foodExample = new FoodExample();
        foodExample.createCriteria().andOwnerEqualTo(userId).andFoodnameEqualTo(foodname);
        List<Food> foods = foodMapper.selectByExample(foodExample);
        if (foods.size() == 0) {
            // 新增食品至食品列表
            Food myFood = dietDiary.getMyFood();
            foodMapper.insertSelective(new Food(null, userId, myFood.getFoodname(), myFood.getCalories(), myFood.getCarbohydrate(), myFood.getFat(), myFood.getProtein(), myFood.getPortionsize()));

        }

        // 查詢 Food 並更新 dietDiary。
        foodExample.createCriteria().andFoodnameEqualTo(foodname);
        List<Food> foods1 = foodMapper.selectByExample(foodExample);
        dietDiary.setMyFood(foods1.get(0));
        dietDiary.setFood(dietDiary.getMyFood().getId());

        // 添加至飲食日記。
        dietDiary.setOwner(userId);
        dietDiaryMapper.insert(dietDiary);
    }

    @Override
    public List<Food> deleteMyFood(HttpServletRequest req, Integer id) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 透過 User id 和 Food id 刪除 Food
        FoodExample foodExample = new FoodExample();
        foodExample.createCriteria().andOwnerEqualTo(userId).andIdEqualTo(id);
        foodMapper.deleteByExample(foodExample);

        return getMyFood(req);
    }

    /*
    @Override
    public List<Food> searchMyFood(HttpServletRequest req, String foodName) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 透過 User id 和 foodName 來搜尋相關的 Food
        FoodExample foodExample = new FoodExample();
        foodExample.createCriteria().andOwnerEqualTo(userId).andFoodnameLike("%" + foodName + "%");
        List<Food> foods = foodMapper.selectByExample(foodExample);

        return foods;
    }
    */

    @Override
    public List<Food> searchFoodFromEdamam(String foodName) throws IOException {
        // 獲取 Edamam API 的金鑰，用來設置 URL
        InputStream fis = FoodServiceImpl.class.getClassLoader().getResourceAsStream("Edamam_API.properties");

        Properties pros = new Properties();
        pros.load(fis);
        String app_id = pros.getProperty("Edamam.id");
        String app_key = pros.getProperty("Edamam.key");

        // 參數 ingr='{foodName}' - 對於兩個英文單詞組成的食品名稱，用引號表示為一個完整的食品，例如 White Rice => 'White Rice'。
        String url = "https://api.edamam.com/api/food-database/v2/parser?app_id={app_id}&app_key={app_key}&ingr='{foodName}'&nutrition-type=logging";

        // 創建參數映射
        Map<String, String> params = new HashMap<>();
        params.put("app_id", app_id);
        params.put("app_key", app_key);
        params.put("foodName", foodName);

        // 發送 GET 請求，返回響應體字串
        String response = restTemplate.getForObject(url, String.class, params);

        // 將響應體字串轉換成 JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        // 抽取 JsonNode 中需要的部分並設置為 Food 物件
        List<Food> foods = new ArrayList<>();
        JsonNode jsonNode_hints = jsonNode.get("hints");

        if (jsonNode_hints.size() > 0) {
            for (int i = 0; i < jsonNode_hints.size(); ++i) {
                JsonNode jsonNode_food = jsonNode_hints.get(i).get("food");

                if (jsonNode_food.size() > 0) {
                    // 食品名稱，並去除字串前後的雙引號 "。
                    String foodname = String.valueOf(jsonNode_food.get("label")).replaceAll("^\"+|\"+$", "");
                    // nutrients 營養素
                    JsonNode jsonNode_nutrients = jsonNode_food.get("nutrients");

                    if (jsonNode_nutrients.size() > 0) {
                        Float calories = jsonNode_nutrients.get("ENERC_KCAL").floatValue(); // 卡路里
                        Float protein = jsonNode_nutrients.get("PROCNT").floatValue(); // 蛋白質
                        Float fat = jsonNode_nutrients.get("FAT").floatValue(); // 脂肪
                        Float carbohydrate = jsonNode_nutrients.get("CHOCDF").floatValue(); // 碳水化合物
                        Short portionsize = 100;

                        Food food = new Food(0, 0, foodname, calories, carbohydrate, fat, protein, portionsize);
                        foods.add(food);
                    }
                }
            }
        }
        return foods;
    }
}
