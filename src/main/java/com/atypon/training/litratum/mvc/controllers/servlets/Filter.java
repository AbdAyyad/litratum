package com.atypon.training.litratum.mvc.controllers.servlets;

import com.atypon.training.litratum.mvc.model.Action;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.StringTokenizer;

public class Filter implements javax.servlet.Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if (url.endsWith("css") || url.endsWith("jsp") || url.endsWith("js") || url.endsWith("html")) {
            return;
        }
        Action action = getAction(url);
        FrontController.setAction(action);
        FrontController.getRequestDispatcher(config.getServletContext()).forward(servletRequest, servletResponse);
    }

    private Action getAction(String fullUrl) {
        int idx = fullUrl.indexOf("/");
        String url = fullUrl.substring(idx + 1);
        StringTokenizer tok = new StringTokenizer(url, "/");
        String className = tok.nextToken();
        String args = "";
        if (tok.hasMoreTokens()) {
            args = tok.nextToken();
        }
        return new Action(className, args);
    }
}
