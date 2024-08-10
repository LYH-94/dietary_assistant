package com.lyh.dietary_assistant.util;

import com.lyh.dietary_assistant.pojo.BodyCompositionData;
import com.lyh.dietary_assistant.pojo.DietDiary;
import com.lyh.dietary_assistant.pojo.Food;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ObjectConverter {
    public DietDiary convertToDietDiary(Map<String, Object> jsonData) throws ParseException {
        DietDiary dietDiary = new DietDiary();
        dietDiary.setId((Integer) jsonData.get("id"));
        dietDiary.setOwner((Integer) jsonData.get("owner"));
        dietDiary.setThreemeals((String) jsonData.get("threeMeals"));

        // Object => Integer => Short
        Integer dietDiary_portionSize = (Integer) jsonData.get("portionSize");
        dietDiary.setPortionsize(dietDiary_portionSize.shortValue());

        // Object => String => Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse((String) jsonData.get("date"));
        dietDiary.setDate(date);

        //String format = formatter.format(date);
        //System.out.println(format);

        // Object => Map
        Food food = new Food();
        LinkedHashMap map_food = (LinkedHashMap) jsonData.get("food");
        food.setId((Integer) map_food.get("id"));
        food.setOwner((Integer) map_food.get("owner"));
        food.setFoodname((String) map_food.get("foodName"));

        // Object => Double => Float
        Double calories = (Double) map_food.get("calories");
        Double carbohydrate = (Double) map_food.get("carbohydrate");
        Double fat = (Double) map_food.get("fat");
        Double protein = (Double) map_food.get("protein");

        food.setCalories(calories.floatValue());
        food.setCarbohydrate(carbohydrate.floatValue());
        food.setFat(fat.floatValue());
        food.setProtein(protein.floatValue());

        // Object => Integer => Short
        Integer food_portionSize = (Integer) map_food.get("portionSize");
        food.setPortionsize(food_portionSize.shortValue());

        dietDiary.setMyFood(food);
        dietDiary.setFood(dietDiary.getMyFood().getId());

        return dietDiary;
    }

    public BodyCompositionData convertToBodyCompositionData(Map<String, String> jsonData, Integer userId) throws ParseException {
        BodyCompositionData bodyCompositionData = new BodyCompositionData();
        bodyCompositionData.setId(Integer.valueOf(jsonData.get("id")));
        bodyCompositionData.setOwner(userId);

        // String => Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(jsonData.get("date"));
        bodyCompositionData.setDate(date);

        // String => Float
        Float weight = Float.valueOf(jsonData.get("weight"));
        Float bodyFat = Float.valueOf(jsonData.get("bodyFat"));
        Float skeletalMuscleMass = Float.valueOf(jsonData.get("skeletalMuscleMass"));

        bodyCompositionData.setWeight(weight);
        bodyCompositionData.setBodyfat(bodyFat);
        bodyCompositionData.setSkeletalmusclemass(skeletalMuscleMass);

        return bodyCompositionData;
    }
}
