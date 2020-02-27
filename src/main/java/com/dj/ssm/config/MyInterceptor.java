package com.dj.ssm.config;

import com.dj.ssm.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component//表示将该类加入到spring容器,作为容器的一个bean
//没有明确指定Bean的ID spring默认将类名的首字母小写,作为Bean的ID
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor");
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
            return false;
        }
        return true;
    }
}
