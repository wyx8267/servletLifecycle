package com.jirengu.hotel.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (PasswordClass.verify(username, password)) {
            // 登录成功
            Cookie cookie = new Cookie("username", username);
            String path = req.getContextPath();
            cookie.setPath(path);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            res.addCookie(cookie);

            Cookie cookie2 = new Cookie("password", password);
            cookie2.setPath(path);
            cookie2.setHttpOnly(true);
            cookie2.setMaxAge(30 * 24 * 60 * 60);
            res.addCookie(cookie2);
            res.getWriter().write("Login success");
        } else {
            // 登录失败
            res.getWriter().write("Login fail");
        }
    }
}
