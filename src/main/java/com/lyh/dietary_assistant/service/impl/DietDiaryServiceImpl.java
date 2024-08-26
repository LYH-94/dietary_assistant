package com.lyh.dietary_assistant.service.impl;

import com.lyh.dietary_assistant.mapper.DietDiaryMapper;
import com.lyh.dietary_assistant.mapper.FoodMapper;
import com.lyh.dietary_assistant.pojo.DietDiary;
import com.lyh.dietary_assistant.pojo.DietDiaryExample;
import com.lyh.dietary_assistant.pojo.Food;
import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.service.DietDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DietDiaryServiceImpl implements DietDiaryService {

    @Autowired
    private DietDiaryMapper dietDiaryMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<DietDiary> getDietDiary(HttpServletRequest req, String date) throws ParseException {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 將 Strgin 型態的 date 轉換成 Date 型態
        // Object => String => Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date formatter_date = formatter.parse(date);

        // 透過用戶 id 查詢飲食日記
        DietDiaryExample dietDiaryExample = new DietDiaryExample();
        dietDiaryExample.createCriteria().andOwnerEqualTo(id).andDateEqualTo(formatter_date);
        List<DietDiary> dietDiaries = dietDiaryMapper.selectByExample(dietDiaryExample);

        // 透過 dietDiaries 中 food 的 id 來查詢 Food
        for (int i = 0; i < dietDiaries.size(); ++i) {
            Food food = foodMapper.selectByPrimaryKey(dietDiaries.get(i).getFood());
            dietDiaries.get(i).setMyFood(food);
        }

        return dietDiaries;
    }

    @Override
    public List<DietDiary> deleteDietDiary(HttpServletRequest req, Integer id, String date) throws ParseException {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 透過 User id 和 DietDiary id 刪除 DietDiary
        DietDiaryExample dietDiaryExample = new DietDiaryExample();
        dietDiaryExample.createCriteria().andOwnerEqualTo(userId).andIdEqualTo(id);
        dietDiaryMapper.deleteByExample(dietDiaryExample);

        // 查詢刪除過後的 DietDiary
        return getDietDiary(req, date);
    }
}
