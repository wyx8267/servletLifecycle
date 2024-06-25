package com.jirengu.hotel;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.*;

public class WebContainer {
    Map<String, String> urlPatternToServletClass = new HashMap<>();

    Map<String, Servlet> urlPatternToServlet = new HashMap<>();

    /**
     * 启动web容器
     */
    public void start(List<ServletConfiguration> configurationList) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ServletException {
        List<ServletConfiguration> servletNeedToLoadOnStartup = new ArrayList<>();
        for (ServletConfiguration servletConfiguration : configurationList) {
            String urlPattern = servletConfiguration.getUrlPattern();
            String servletClass = servletConfiguration.getServletClass();
            urlPatternToServletClass.put(urlPattern, servletClass);
            Integer loadOnStartup = servletConfiguration.getLoadOnStartup();
            if(loadOnStartup != null && loadOnStartup >= 0){
                // loadOnStartup有优先级
                servletNeedToLoadOnStartup.add(servletConfiguration);
            }
        }
        // 按照 loadOnStartup 优先级进行升序排序
        servletNeedToLoadOnStartup.sort(Comparator.comparing(v -> v.getLoadOnStartup()));
        // 创建servlet对象
        for (ServletConfiguration servletConfiguration : servletNeedToLoadOnStartup) {
            String servletClass = servletConfiguration.getServletClass();
            String urlPattern = servletConfiguration.getUrlPattern();
            createServletObj(urlPattern, servletClass);
        }
    }

    private Servlet createServletObj(String urlPattern, String servletClass) throws InstantiationException, IllegalAccessException, ServletException, ClassNotFoundException {
        Class clazz = Class.forName(servletClass);
        Object obj = clazz.newInstance();
        Servlet servlet = (Servlet) obj;
        servlet.init(null);
        urlPatternToServlet.put(urlPattern, servlet);
        return servlet;
    }

    /**
     * 执行请求
     */
    public void doService(String urlPattern, ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 情况一：如果请求路径和 servlet 之间的映射关系存在，直接获取servlet对象并调用其service方法
        if(urlPatternToServlet.containsKey(urlPattern)){
            Servlet servlet = urlPatternToServlet.get(urlPattern);
            servlet.service(servletRequest, servletResponse);
            return;
        }
        // 情况二：如果请求路径和 servlet 之间的映射关系还不存在，但是请求路径和servlet类名之间的映射关系存在，那么创建servlet对象并调用其service方法
        if(urlPatternToServletClass.containsKey(urlPattern)){
            String servletClass = urlPatternToServletClass.get(urlPattern);
            Servlet servlet = createServletObj(urlPattern, servletClass);
            servlet.service(servletRequest, servletResponse);
            return;
        }
        // 情况三：请求路径不存在，无法处理用户请求
        System.out.println("请求路径不存在，无法处理");
    }

    /**
     * 关闭web容器
     */
    public void close(){
        for (Servlet servlet : urlPatternToServlet.values()) {
            servlet.destroy();
        }
    }
}
