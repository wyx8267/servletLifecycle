package com.jirengu.hotel.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCookieServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Cookie cookie1 = new Cookie("username", "mirai");
        cookie1.setMaxAge(1000);
        cookie1.setHttpOnly(true);
        Cookie cookie2 = new Cookie("age", "32");
        cookie2.setMaxAge(10000);
        String path = req.getContextPath();
        cookie2.setPath(path);
//        cookie2.setPath("/");
        res.addCookie(cookie1);
        res.addCookie(cookie2);
    }
}
