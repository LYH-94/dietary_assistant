package com.lyh.dietary_assistant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyh.dietary_assistant.pojo.Food;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FoodService {
    // 查詢我的食品頁需要的數據
    List<Food> getMyFood(HttpServletRequest req);

    // 更新指定的食品並返回更新後的所有食品
    List<Food> putMyFood(HttpServletRequest req, Map<String, String> allParams);

    // 新增食品至飲食日記並返回飲食日記頁。若用戶的食品列表中沒有該食品紀錄，則先添加至用戶的食品列表中。
    public void postMyFood(HttpServletRequest req, Map<String, Object> jsonData) throws ParseException;

    // 刪除指定的食品並返回刪除完成後的所有食品
    public List<Food> deleteMyFood(HttpServletRequest req, Integer id);

    // 從用戶的食品中查詢與 foodName 相關的食品
    public List<Food> searchMyFood(HttpServletRequest req, String foodName);

    // 從第三方資料庫(Edamam API)中查詢與 foodName 相關的食品
    public List<Food> searchFoodFromEdamam(String foodName) throws JsonProcessingException;
}