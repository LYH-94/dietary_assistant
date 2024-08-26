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

        // Object => Map
        Food food = new Food();
        LinkedHashMap map_food = (LinkedHashMap) jsonData.get("food");
        food.setId((Integer) map_food.get("id"));
        food.setOwner((Integer) map_food.get("owner"));
        food.setFoodname((String) map_food.get("foodName"));

        // Object => Double => Float
        // calories
        Object calories_object = map_food.get("calories");
        if (calories_object instanceof Double) {
            Double calories_Double = (Double) calories_object;
            food.setCalories(calories_Double.floatValue());
        } else if (calories_object instanceof Integer) {
            Integer calories_Integer = (Integer) calories_object;
            food.setCalories(calories_Integer.floatValue());
        }
        // carbohydrate
        Object carbohydrate_object = map_food.get("carbohydrate");
        if (carbohydrate_object instanceof Double) {
            Double carbohydrate_Double = (Double) carbohydrate_object;
            food.setCarbohydrate(carbohydrate_Double.floatValue());
        } else if (carbohydrate_object instanceof Integer) {
            Integer carbohydrate_Integer = (Integer) carbohydrate_object;
            food.setCarbohydrate(carbohydrate_Integer.floatValue());
        }
        // fat
        Object fat_object = map_food.get("fat");
        if (fat_object instanceof Double) {
            Double fat_Double = (Double) fat_object;
            food.setFat(fat_Double.floatValue());
        } else if (fat_object instanceof Integer) {
            Integer fat_Integer = (Integer) fat_object;
            food.setFat(fat_Integer.floatValue());
        }
        // protein
        Object protein_object = map_food.get("protein");
        if (protein_object instanceof Double) {
            Double protein_Double = (Double) protein_object;
            food.setProtein(protein_Double.floatValue());
        } else if (protein_object instanceof Integer) {
            Integer protein_Integer = (Integer) protein_object;
            food.setProtein(protein_Integer.floatValue());
        }

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
