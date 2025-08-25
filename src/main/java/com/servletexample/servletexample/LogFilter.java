//package com.servletexample.servletexample;
//
//
//import jakarta.servlet.*;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletRequest;
//import java.io.IOException;
//
//public class LogFilter implements Filter {
//    public void init() {}
//    public void destroy() {}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        System.out.println("Request received at " + new java.util.Date());
//        chain.doFilter(request, response);
//        System.out.println("Response sent at " + new java.util.Date());
//    }
//}
