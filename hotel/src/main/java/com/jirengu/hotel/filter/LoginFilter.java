package com.jirengu.hotel.filter;

import com.jirengu.hotel.cookie.PasswordClass;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = "/cookie/*")
public class LoginFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 如果请求的是登录 Servlet，直接放行请求
        if ("/cookie/login".equals(req.getServletPath())){
            System.out.println("是登录的请求路径，直接放行请求");
            chain.doFilter(req, res);
            return;
        }

        String username = getCookieValue(req, "username");
        String password = getCookieValue(req, "password");
        if (username == null || password == null) {
            res.sendRedirect("/hotel/login.html");
        } else {
            // 校验
            if (PasswordClass.verify(username, password)) {
                // 校验通过，放行请求
                System.out.println("校验通过，放行请求");
                chain.doFilter(req, res);
            } else {
                String path = req.getContextPath();
                Cookie cookie1 = new Cookie("username", username);
                cookie1.setPath(path);
                cookie1.setMaxAge(0);
                res.addCookie(cookie1);
                Cookie cookie2 = new Cookie("password", password);
                cookie2.setPath(path);
                cookie2.setMaxAge(0);
                res.addCookie(cookie2);
                res.sendRedirect("/hotel/login.html");
            }
        }
    }

    private String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
