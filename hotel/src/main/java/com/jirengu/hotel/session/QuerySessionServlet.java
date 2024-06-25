package com.jirengu.hotel.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QuerySessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            // session还不存在，请求次数 = 0
            res.getWriter().write("query count = 0");
        } else {
            Object obj = session.getAttribute("count");
            if (obj == null) {
                // 次数还不存在，请求次数 = 0
                res.getWriter().write("query count = 0");
            } else {
                res.getWriter().write("query count = " + obj);
            }
        }
    }
}
