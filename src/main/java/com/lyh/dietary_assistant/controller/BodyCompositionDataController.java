package com.lyh.dietary_assistant.controller;

import com.lyh.dietary_assistant.pojo.BodyCompositionData;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface BodyCompositionDataController {
    // 查詢我的圖表頁需要的數據
    List<BodyCompositionData> getMyChart(HttpServletRequest req);

    // 新增用戶體態數據成功返回新增完成後的所有體態數據
    List<BodyCompositionData> postMyChart(HttpServletRequest req, Map<String, String> allParams) throws ParseException;

    // 更新指定的體態數據並返回更新後的所有數據
    List<BodyCompositionData> putMyChart(HttpServletRequest req, Map<String, String> allParams) throws ParseException;

    // 刪除指定的體態數據並返回刪除數據後的所有體態數據
    List<BodyCompositionData> deleteMyChart(HttpServletRequest req, Integer id);
}
