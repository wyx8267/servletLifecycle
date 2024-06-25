package com.jirengu.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MyFilterChain implements FilterChain {
    private ArrayList<Filter> filterList;

    private Servlet servlet;

    private int point = 0;

    public MyFilterChain(ArrayList<Filter> filterList, Servlet servlet) {
        if(filterList == null) {
            filterList = new ArrayList<>();
        }
        filterList.add(new FilterProxy(servlet));

        this.filterList = filterList;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        if (point < filterList.size()) {
            Filter filter = filterList.get(point);
            point++;
            filter.doFilter(servletRequest, servletResponse, this);
        }
    }

    private static class FilterProxy extends HttpFilter {

        private Servlet servlet;

        public FilterProxy(Servlet servlet) {
            this.servlet = servlet;
        }

        @Override
        protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
            servlet.service(req, res);
        }
    }
}
