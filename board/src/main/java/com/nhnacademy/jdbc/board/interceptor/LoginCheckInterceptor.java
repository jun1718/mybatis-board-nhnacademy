package com.nhnacademy.jdbc.board.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {
    private final List<String> urlList = new ArrayList();

    public void whiteList() {
        urlList.add("/login");
        urlList.add("/");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        whiteList();

        if (!urlList.contains(request.getRequestURI())) {
            if ((Objects.isNull(session)) || Objects.isNull(session.getAttribute("id"))) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        urlList.clear();
    }

}
