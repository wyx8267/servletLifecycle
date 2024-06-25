package com.jirengu.hotel;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetServlet extends HttpServlet {
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("GetServlet service is called.");
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("password"));
//
//        res.getWriter().write("GetServlet success.");
//        throw new RuntimeException("GetServlet exception.");
//    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("GetServlet service is called.");
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));

        res.getWriter().write("GetServlet success.");

    }
}
