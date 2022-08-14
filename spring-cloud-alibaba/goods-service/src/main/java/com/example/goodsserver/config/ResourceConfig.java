package com.example.goodsserver.config;

import com.example.goodsserver.interceptor.ApiRequestLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Autowired
    ApiRequestLimitInterceptor apiRequestLimitInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRequestLimitInterceptor)
                .addPathPatterns("/**");
    }


}
