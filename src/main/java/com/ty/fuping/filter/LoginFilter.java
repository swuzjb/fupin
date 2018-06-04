package com.ty.fuping.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZhanJingbo
 * @version 1.0.0
 * Created on 2018/5/30
 */
@Order(1)
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static final String LOGIN_URL = "/fupin/login";
    private static final String INSTALL_URL = "/fupin/install";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        if (LOGIN_URL.equals(url) || INSTALL_URL.equals(url)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (null != request.getSession().getAttribute("userName")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendError(403, "no login");
    }

    @Override
    public void destroy() {

    }
}
