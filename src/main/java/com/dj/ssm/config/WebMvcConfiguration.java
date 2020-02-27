package com.dj.ssm.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@DependsOn("myInterceptor")//表示该类初始化时强制依赖某个Bean(@Autowired注入myInterceptor时报错时使用)
@RequiredArgsConstructor(onConstructor = @_(@Autowired))//lombok提供
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    // final MyInterceptor myInterceptor;lombok提供

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //向容器注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
        //拦截请求
        interceptorRegistration.addPathPatterns("/**");
        //放过请求
        interceptorRegistration.excludePathPatterns("/user/toLogin");
        interceptorRegistration.excludePathPatterns("/user/login");
    }
}
