package com.lyh.dietary_assistant.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
        "/",
        "/logout",
        "/page/index.html",
        "/index",
        "/page/userInfo.html",
        "/userInfo",
        "/page/dietDiary.html",
        "/dietDiary/*",
        "/page/myFood.html",
        "/myFood",
        "/myFood/*",
        "/Edamam/*",
        "/page/myChart.html",
        "/myChart",
        "/myChart/*"})
//@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 排除不需要身分驗證的請求。
        /*
        String path = httpRequest.getRequestURI();
        if (!(path.equals("/dietary_assistant/page/login.html")
                || path.equals("/dietary_assistant/login")
                || path.equals("/dietary_assistant/page/signup.html")
                || path.equals("/dietary_assistant/signup"))) {

        }
        */

        // 獲取 session 中的用戶訊息。
        Object user = httpRequest.getSession().getAttribute("User");

        // 驗證用戶。
        if (user == null) {
            // 未登入，重定向到登录页面
            System.out.println("未登入");
            httpRequest.getRequestDispatcher("/forwardLoginPage").forward(httpRequest, httpResponse);
            return;
        }

        // 用戶已登入，繼續處理請求。
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
