package com.gtbr.arcanebank.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestScope
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        if (uri.equals("/") || uri.equals("/login") || uri.equals("/validar-login") || uri.contains("assets")) {
            return true;
        }
        if (request.getSession().getAttribute("usuarioLogado") != null) {
            if (uri.equals("/login") || uri.equals("/validar-login")){
                response.sendRedirect("/");
                return false;
            }else {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}