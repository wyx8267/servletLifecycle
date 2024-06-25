package com.jirengu.hotel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class DemoServlet extends MyGenericServlet{

    @Override
    public void init() {
        System.out.println("初始化执行");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("此处编写业务逻辑");
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
