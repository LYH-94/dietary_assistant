package com.lyh.dietary_assistant.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyh.dietary_assistant.controller.FoodController;
import com.lyh.dietary_assistant.pojo.Food;
import com.lyh.dietary_assistant.service.FoodService;
import com.lyh.dietary_assistant.util.ResponseHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class FoodControllerImpl implements FoodController {
    private FoodService foodService;

    @Autowired
    public FoodControllerImpl(FoodService foodService) {
        this.foodService = foodService;
    }

    @Autowired
    private ResponseHTML responseHTML;

    @Override
    @RequestMapping(value = "/myFood", method = RequestMethod.GET)
    @ResponseBody
    public List<Food> getMyFood(HttpServletRequest req) {
        return foodService.getMyFood(req);
    }

    @Override
    @RequestMapping(value = "/myFood", method = RequestMethod.PUT)
    @ResponseBody
    public List<Food> putMyFood(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
        return foodService.putMyFood(req, allParams);
    }

    @Override
    @RequestMapping(value = "/myFood", method = RequestMethod.POST)
    public ResponseEntity<byte[]> postMyFood(HttpServletRequest req, @RequestBody Map<String, Object> jsonData) throws Exception {
        foodService.postMyFood(req, jsonData);
        return responseHTML.getHTML("dietDiary", HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/myFood/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<Food> deleteMyFood(HttpServletRequest req, @PathVariable(value = "id") Integer id) {
        return foodService.deleteMyFood(req, id);
    }

    /*
    @Override
    @RequestMapping(value = "/myFood/{foodName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Food> searchMyFood(HttpServletRequest req, @PathVariable(value = "foodName") String foodName) {
        return foodService.searchMyFood(req, foodName);
    }
    */

    @Override
    @RequestMapping(value = "/Edamam/{foodName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Food> searchFoodFromEdamam(@PathVariable(value = "foodName") String foodName) throws IOException {
        return foodService.searchFoodFromEdamam(foodName);
    }
}
