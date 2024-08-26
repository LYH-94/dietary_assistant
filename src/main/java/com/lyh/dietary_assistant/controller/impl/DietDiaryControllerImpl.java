package com.lyh.dietary_assistant.controller.impl;

import com.lyh.dietary_assistant.controller.DietDiaryController;
import com.lyh.dietary_assistant.pojo.DietDiary;
import com.lyh.dietary_assistant.service.DietDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class DietDiaryControllerImpl implements DietDiaryController {

    private DietDiaryService dietDiaryService;

    @Autowired
    public DietDiaryControllerImpl(DietDiaryService dietDiaryService) {
        this.dietDiaryService = dietDiaryService;
    }

    @Override
    @RequestMapping(value = "/dietDiary/{date}")
    @ResponseBody
    public List<DietDiary> getDietDiary(HttpServletRequest req, @PathVariable(value = "date") String date) throws ParseException {
        return dietDiaryService.getDietDiary(req, date);
    }

    @Override
    @RequestMapping(value = "/dietDiary/{id}/{date}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<DietDiary> deleteDietDiary(HttpServletRequest req, @PathVariable(value = "id") Integer id, @PathVariable(value = "date") String date) throws ParseException {
        return dietDiaryService.deleteDietDiary(req, id, date);
    }
}
