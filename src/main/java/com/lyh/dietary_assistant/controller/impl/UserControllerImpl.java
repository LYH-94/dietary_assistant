package com.lyh.dietary_assistant.controller.impl;

import com.lyh.dietary_assistant.controller.UserController;
import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.pojo.UserInfoNickName;
import com.lyh.dietary_assistant.service.UserService;
import com.lyh.dietary_assistant.util.ResponseHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class UserControllerImpl implements UserController {
    private UserService userService;

    @Autowired
    private ResponseHTML responseHTML;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @RequestMapping(value = "/login")
    public ResponseEntity<byte[]> login(HttpServletRequest req, @RequestParam Map<String, String> allParams) throws IOException {
        return userService.login(req, allParams) ? responseHTML.getHTML("index", HttpStatus.OK) : responseHTML.getHTML("login", HttpStatus.UNAUTHORIZED);
    }

    @Override
    @RequestMapping(value = "/logout")
    public ResponseEntity<byte[]> logout(HttpServletRequest req) throws IOException {
        userService.logout(req);
        return responseHTML.getHTML("login", HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/signup")
    public ResponseEntity<byte[]> signup(HttpServletRequest req, @RequestParam Map<String, String> allParams) throws IOException {
        return userService.signup(req, allParams) ? responseHTML.getHTML("index", HttpStatus.OK) : responseHTML.getHTML("signup", HttpStatus.BAD_REQUEST);
    }

    @Override
    @RequestMapping(value = "/index")
    @ResponseBody
    public UserInfoNickName index(HttpServletRequest req) {
        return userService.getUserInfoNickName(req);
    }

    @Override
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(HttpServletRequest req) {
        return userService.getUserInfo(req);
    }

    @Override
    @RequestMapping(value = "/userInfo", method = RequestMethod.PUT)
    @ResponseBody
    public User putUserInfo(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
        return userService.putUserInfo(req, allParams);
    }

    @Override
    @RequestMapping(value = "/forwardLoginPage")
    public ResponseEntity<byte[]> forwardLoginPage() throws IOException {
        return responseHTML.getHTML("login", HttpStatus.UNAUTHORIZED);
    }
}
