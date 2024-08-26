package com.lyh.dietary_assistant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyh.dietary_assistant.pojo.Food;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface FoodController {
    // 查詢我的食品頁需要的數據
    List<Food> getMyFood(HttpServletRequest req);

    // 更新指定的食品並返回更新後的所有食品
    List<Food> putMyFood(HttpServletRequest req, Map<String, String> allParams);

    // 新增食品至飲食日記並返回飲食日記頁。若用戶的食品列表中沒有該食品紀錄，則先添加至用戶的食品列表中。
    public ResponseEntity<byte[]> postMyFood(HttpServletRequest req, Map<String, Object> jsonData) throws Exception;

    // 刪除指定的食品並返回刪除完成後的所有食品
    public List<Food> deleteMyFood(HttpServletRequest req, Integer id);

    // 從用戶的食品中查詢與 foodName 相關的食品。(該功能在前端 JavaScript 中完成了，後端就不用了，暫存於此。)
    //public List<Food> searchMyFood(HttpServletRequest req, String foodName);

    // 從第三方資料庫(Edamam API)中查詢與 foodName 相關的食品
    public List<Food> searchFoodFromEdamam(String foodName) throws JsonProcessingException;
}
