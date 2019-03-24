package com.hgd.ebp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="PerformanceFilter", urlPatterns={"/*"})
public class PerformanceFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        long begin = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();

        StringBuffer logMessage = 
        		((HttpServletRequest) request).getRequestURL();
        logMessage.append("] = ");
        logMessage.append(end - begin);
        logMessage.append("ms");
        logMessage.insert(0, "–‘ƒ‹≤‚ ‘£∫[");

        config.getServletContext().log(logMessage.toString());
    }

    public void destroy() {}
}