package com.lyh.dietary_assistant.controller;

import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.pojo.UserInfoNickName;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface UserController {
    // 請求 URL "/" - 返回 index.html
    public ResponseEntity<byte[]> indexPage() throws IOException;

    // 登入用戶，
    public ResponseEntity<byte[]> login(HttpServletRequest req, Map<String, String> allParams) throws IOException;

    // 登出用戶。
    public ResponseEntity<byte[]> logout(HttpServletRequest req) throws IOException;

    // 註冊用戶。
    public ResponseEntity<byte[]> signup(HttpServletRequest req, Map<String, String> allParams) throws IOException;

    // 透過伺服器內部轉發來響應 Login.html 並設置狀態碼為 401。
    public ResponseEntity<byte[]> forwardLoginPage() throws IOException;

    // 獲取 index.html 所需數據 (UserInfoNickName)。
    public UserInfoNickName index(HttpServletRequest req);

    // 獲取用戶資訊。
    public User getUserInfo(HttpServletRequest req);

    // 更新用戶資訊並返回更新後的資訊。
    public User putUserInfo(HttpServletRequest req, Map<String, String> allParams);
}
