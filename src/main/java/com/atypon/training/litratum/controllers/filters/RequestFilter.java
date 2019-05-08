package com.atypon.training.litratum.controllers.filters;

import com.atypon.training.litratum.controllers.servlets.FrontController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements javax.servlet.Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String url = httpRequest.getRequestURI();
        if (isStaticResource(url)) {
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(url);
            dispatcher.forward(servletRequest, servletResponse);
            return;
        }
        httpRequest.setAttribute("actionUrl", url);
        FrontController.getRequestDispatcher(config.getServletContext()).forward(servletRequest, servletResponse);
    }

    private boolean isStaticResource(String url) {
        return url.endsWith("css") || url.endsWith("jsp") || url.endsWith("js") || url.endsWith("html") || url.endsWith("ico");
    }

}
