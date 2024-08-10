package com.lyh.dietary_assistant.service;

import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.pojo.UserInfoNickName;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    // 用戶登入。
    public boolean login(HttpServletRequest req, Map<String, String> allParams);

    // 用戶登出。
    public void logout(HttpServletRequest req);

    // 註冊用戶。
    public boolean signup(HttpServletRequest req, Map<String, String> allParams);

    // 獲取用戶暱稱。
    public UserInfoNickName getUserInfoNickName(HttpServletRequest req);

    // 獲取用戶資訊。
    public User getUserInfo(HttpServletRequest req);

    // 更新用戶資訊並返回更新後的資訊。
    public User putUserInfo(HttpServletRequest req, Map<String, String> allParams);
}
