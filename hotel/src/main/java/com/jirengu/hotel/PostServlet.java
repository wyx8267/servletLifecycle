package com.jirengu.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostServlet extends HttpServlet {
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("PostServlet service is called.");
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("password"));
//
//        res.getWriter().write("PostServlet success.");
//    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("PostServlet service is called.");
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        // 第一步，从请求的body中读取json数据
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(
                req.getInputStream(), "UTF-8"
        ));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }
        // 第二步，把json数据转成对象
        ObjectMapper objectMapper = new ObjectMapper();
        UserPassword userPassword = objectMapper.readValue(sb.toString(), UserPassword.class);
        System.out.println(userPassword);
        String json = objectMapper.writeValueAsString(userPassword);
        System.out.println(json);
        res.getWriter().write("PostServlet success.");
    }
}
