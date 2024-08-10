package com.lyh.dietary_assistant.controller.impl;

import com.lyh.dietary_assistant.controller.BodyCompositionDataController;
import com.lyh.dietary_assistant.pojo.BodyCompositionData;
import com.lyh.dietary_assistant.service.BodyCompositionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
public class BodyCompositionDataControllerImpl implements BodyCompositionDataController {
    private BodyCompositionDataService bodyCompositionDataService;

    @Autowired
    public BodyCompositionDataControllerImpl(BodyCompositionDataService bodyCompositionDataService) {
        this.bodyCompositionDataService = bodyCompositionDataService;
    }

    @Override
    @RequestMapping(value = "/myChart", method = RequestMethod.GET)
    @ResponseBody
    public List<BodyCompositionData> getMyChart(HttpServletRequest req) {
        return bodyCompositionDataService.getMyChart(req);
    }

    @Override
    @RequestMapping(value = "/myChart", method = RequestMethod.POST)
    @ResponseBody
    public List<BodyCompositionData> postMyChart(HttpServletRequest req, @RequestParam Map<String, String> allParams) throws ParseException {
        return bodyCompositionDataService.postMyChart(req, allParams);
    }

    @Override
    @RequestMapping(value = "/myChart", method = RequestMethod.PUT)
    @ResponseBody
    public List<BodyCompositionData> putMyChart(HttpServletRequest req, @RequestParam Map<String, String> allParams) throws ParseException {
        return bodyCompositionDataService.putMyChart(req, allParams);
    }

    @Override
    @RequestMapping(value = "/myChart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<BodyCompositionData> deleteMyChart(HttpServletRequest req, @PathVariable Integer id) {
        return bodyCompositionDataService.deleteMyChart(req, id);
    }
}
