package com.jirengu.hotel.cookie;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class QueryOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(10);
        ServletContext servletContext = getServletContext();
        System.out.println(servletContext);
        String username = getCookieValue(req, "username");
        // 用户已登录，执行业务逻辑
        System.out.println(username + " is querying order.");
        resp.getWriter().write(username + " is querying order.");
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
