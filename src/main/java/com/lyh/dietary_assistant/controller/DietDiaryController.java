package com.lyh.dietary_assistant.controller;

import com.lyh.dietary_assistant.pojo.DietDiary;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DietDiaryController {
    // 查詢飲食日記頁需要的數據(查詢指定日期及前後三天的飲食紀錄)。
    List<DietDiary> getDietDiary(HttpServletRequest req, String date);

    // 刪除指定 id 的飲食紀錄並返回指定日期及前後三天的飲食紀錄(刪除紀錄後的紀錄)
    List<DietDiary> deleteDietDiary(HttpServletRequest req, Integer id, String date);
}
