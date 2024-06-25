package com.jirengu.hotel.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryCartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("QueryCartServlet is called");
        HttpSession session = req.getSession(true);
        Object obj = session.getAttribute("cart");
        if (obj == null) {
            // 如果“cart”属性不存在，说明还没加入购物车
            res.getWriter().write("nothing in cart.");
        } else {
            // 如果“cart”属性存在，添加本次商品
            List<String> productList = (List<String>) obj;
            res.getWriter().write("products in cart: " + productList);
        }
    }
}
