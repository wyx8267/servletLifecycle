package com.jirengu.hotel.cookie;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        if(PasswordClass.verify(username, oldPassword)){
            PasswordClass.registerUser(username, newPassword);
            res.getWriter().write("change success");
        }else{
            // 用户名与旧密码不匹配，无法修改
            res.getWriter().write("username and password not match, change fail");
        }
    }
}
