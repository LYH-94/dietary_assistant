package com.lyh.dietary_assistant.service.impl;

import com.lyh.dietary_assistant.mapper.BodyCompositionDataMapper;
import com.lyh.dietary_assistant.pojo.BodyCompositionData;
import com.lyh.dietary_assistant.pojo.BodyCompositionDataExample;
import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.service.BodyCompositionDataService;
import com.lyh.dietary_assistant.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BodyCompositionDataServiceImpl implements BodyCompositionDataService {

    @Autowired
    private BodyCompositionDataMapper bodyCompositionDataMapper;

    @Autowired
    private ObjectConverter objectConverter;

    @Override
    public List<BodyCompositionData> getMyChart(HttpServletRequest req) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 透過用戶 id 查詢所有 BodyCompositionData
        BodyCompositionDataExample bodyCompositionDataExample = new BodyCompositionDataExample();
        bodyCompositionDataExample.createCriteria().andOwnerEqualTo(id);
        List<BodyCompositionData> bodyCompositionData = bodyCompositionDataMapper.selectByExample(bodyCompositionDataExample);

        return bodyCompositionData;
    }

    @Override
    public List<BodyCompositionData> postMyChart(HttpServletRequest req, Map<String, String> allParams) throws ParseException {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        BodyCompositionData bodyCompositionData = objectConverter.convertToBodyCompositionData(allParams, userId);

        // 查詢是否已經有相同日期的體態數據，若有則不新增數據，若無則新增。
        BodyCompositionDataExample bodyCompositionDataExample = new BodyCompositionDataExample();
        bodyCompositionDataExample.createCriteria().andOwnerEqualTo(userId).andDateEqualTo(bodyCompositionData.getDate());
        List<BodyCompositionData> bodyCompositionDataList = bodyCompositionDataMapper.selectByExample(bodyCompositionDataExample);

        if (bodyCompositionDataList.size() == 0) {
            // 新增用戶體態數據
            bodyCompositionDataMapper.insert(bodyCompositionData);
        }

        return getMyChart(req);
    }

    @Override
    public List<BodyCompositionData> putMyChart(HttpServletRequest req, Map<String, String> allParams) throws ParseException {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 將參數轉換成 BodyCompositionData 物件
        BodyCompositionData bodyCompositionData = objectConverter.convertToBodyCompositionData(allParams, userId);
        bodyCompositionData.setId(null); // 修改不能改主鍵，因此設為 null。

        // 修改用戶體態數據
        BodyCompositionDataExample bodyCompositionDataExample = new BodyCompositionDataExample();
        bodyCompositionDataExample.createCriteria().andOwnerEqualTo(userId).andDateEqualTo(bodyCompositionData.getDate());
        bodyCompositionDataMapper.updateByExampleSelective(bodyCompositionData, bodyCompositionDataExample);

        return getMyChart(req);
    }

    @Override
    public List<BodyCompositionData> deleteMyChart(HttpServletRequest req, Integer id) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer userId = user.getId();

        // 根據 userId 和 id 來刪除用戶體態數據
        BodyCompositionDataExample bodyCompositionDataExample = new BodyCompositionDataExample();
        bodyCompositionDataExample.createCriteria().andOwnerEqualTo(userId).andIdEqualTo(id);
        bodyCompositionDataMapper.deleteByExample(bodyCompositionDataExample);

        return getMyChart(req);
    }
}
