package com.lyh.dietary_assistant.service.impl;

import com.lyh.dietary_assistant.mapper.UserInfoMapper;
import com.lyh.dietary_assistant.mapper.UserMapper;
import com.lyh.dietary_assistant.pojo.*;
import com.lyh.dietary_assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static java.lang.Byte.parseByte;
import static java.lang.Short.parseShort;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean login(HttpServletRequest req, Map<String, String> allParams) {
        String account = allParams.get("account");
        String password = allParams.get("password");

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) {
            User user = users.get(0);
            HttpSession session = req.getSession();
            session.setAttribute("User", user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        session.invalidate();
    }

    @Override
    public boolean signup(HttpServletRequest req, Map<String, String> allParams) {
        String account = allParams.get("account");
        String password = allParams.get("password");
        String nickName = allParams.get("nickName");
        String email = allParams.get("email");

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() == 0) {
            // 添加 User
            userMapper.insert(new User(0, account, password));
            // 查詢 User 並添加到 session
            userExample.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
            users = userMapper.selectByExample(userExample);
            User user = users.get(0);
            HttpSession session = req.getSession();
            session.setAttribute("User", user);
            // 添加 UserInfo
            userInfoMapper.insertSelective(new UserInfo(0, user.getId(), nickName, email, null, null, null, null));
            return true;
        } else { // users.size() != 0 表示帳號密碼有重複。
            return false;
        }
    }

    @Override
    public UserInfoNickName getUserInfoNickName(HttpServletRequest req) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 透過 id 查詢用戶資訊中的 nickName
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andOwnerEqualTo(id);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos.size() != 0) {
            return new UserInfoNickName(userInfos.get(0).getNickname());
        }
        return null;
    }

    @Override
    public User getUserInfo(HttpServletRequest req) {
        // 獲取 session 中的用戶 id
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 透過 id 查詢用戶資訊
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andOwnerEqualTo(id);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos.size() != 0) {
            user.setUserInfo(userInfos.get(0));
            return user;
        }
        return null;
    }

    @Override
    public User putUserInfo(HttpServletRequest req, Map<String, String> allParams) {
        // 獲取 allParams 中的數據
        String account = allParams.get("account");
        String password = allParams.get("password");
        String nickName = allParams.get("nickName");
        String email = allParams.get("email");
        Short targetCalories = parseShort(allParams.get("targetCalories"));
        Byte carbohydrateRatio = parseByte(allParams.get("carbohydrateRatio"));
        Byte fatRatio = parseByte(allParams.get("fatRatio"));
        Byte proteinRatio = parseByte(allParams.get("proteinRatio"));

        // 獲取 session 中的用戶
        User user = (User) req.getSession().getAttribute("User");
        Integer id = user.getId();

        // 透過 id 更新 User
        userMapper.updateByPrimaryKeySelective(new User(id, null, password));

        // 透過 id 更新 UserInfo
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andOwnerEqualTo(id);
        userInfoMapper.updateByExampleSelective(new UserInfo(null, null, nickName, email, targetCalories, carbohydrateRatio, fatRatio, proteinRatio), userInfoExample);

        // 查詢更新後的 User 和 UserInfo 並
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        user = userMapper.selectByExample(userExample).get(0);

        // 更新 session 中的 User
        req.getSession().setAttribute("User", user);

        // 查詢更新後的 UserInfo
        userInfoExample.createCriteria().andOwnerEqualTo(id);
        UserInfo userInfo = userInfoMapper.selectByExample(userInfoExample).get(0);

        // 設置 User 中的 UserInfo
        user.setUserInfo(userInfo);

        return user;
    }
}
