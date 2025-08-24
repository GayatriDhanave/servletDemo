package com.servletexample.servletexample;

import javax.servlet.*;
import java.io.IOException;

public class LogFilter implements Filter {
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Request received at " + new java.util.Date());
        chain.doFilter(request, response);
        System.out.println("Response sent at " + new java.util.Date());
    }
}
