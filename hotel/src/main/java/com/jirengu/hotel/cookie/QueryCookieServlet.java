package com.jirengu.hotel.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryCookieServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie :cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
    }
}
