package com.jirengu.hotel;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WebContainerTest {
    private List<ServletConfiguration> buildConfiguration(){
        // 不配置loadOnStartup
        ServletConfiguration bookRoomServletConfiguration = new ServletConfiguration(
                "/bookRoom",
                "com.jirengu.hotel.BookRoomServlet",
                null
        );
        // 配置loadOnStartup，但是小于0
        ServletConfiguration queryRoomServletConfiguration = new ServletConfiguration(
                "/queryRoom",
                "com.jirengu.hotel.QueryRoomServlet",
                -1
        );
        // 配置loadOnStartup = 1
        ServletConfiguration checkInRoomServletConfiguration = new ServletConfiguration(
                "/checkIn",
                "com.jirengu.hotel.CheckInServlet",
                1
        );
        // 配置loadOnStartup = 0
        ServletConfiguration checkOutRoomServletConfiguration = new ServletConfiguration(
                "/checkOut",
                "com.jirengu.hotel.CheckOutServlet",
                0
        );
        // 不配置loadOnStartup
        ServletConfiguration endServletConfiguration = new ServletConfiguration(
                "/end",
                "com.jirengu.hotel.EndServlet",
                null
        );
        List<ServletConfiguration> servletConfigurationList = new ArrayList<>();
        servletConfigurationList.add(bookRoomServletConfiguration);
        servletConfigurationList.add(queryRoomServletConfiguration);
        servletConfigurationList.add(checkInRoomServletConfiguration);
        servletConfigurationList.add(checkOutRoomServletConfiguration);
        servletConfigurationList.add(endServletConfiguration);
        return servletConfigurationList;
    }

    @Test
    public void testWebContainer() throws ServletException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        // 获取配置信息
        List<ServletConfiguration> configurationList = buildConfiguration();
        // 创建并启动web容器
        WebContainer webContainer = new WebContainer();
        webContainer.start(configurationList);
        // 发起请求
        webContainer.doService("/queryRoom", null, null);
        webContainer.doService("/bookRoom", null, null);
        webContainer.doService("/checkIn", null, null);
        webContainer.doService("/checkOut", null, null);
        // 不调用/end，结果应该不创建EndServlet
//        webContainer.doService("/end", null, null);
        webContainer.close();

        // 预期结果
        /**
         * CheckOutServlet init is called.
         * CheckInServlet init is called.
         * QueryRoomServlet init is called.
         * QueryRoomServlet service is called. object is
         * BookRoomServlet init is called.
         * 连接数据库
         * BookRoomServlet service is called.
         * 写数据库
         * CheckInServlet service is called.
         * CheckOutServlet service is called.
         * 调用各个servlet destroy方法（除了endServlet）
         */
    }
}