package com.example.goodsserver.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.goodsserver.annotation.RequestLimit;
import com.example.goodsserver.config.ApiRequestLimitConfig;
import com.example.service.api.model.CommonResult;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApiRequestLimitInterceptor implements HandlerInterceptor {


    @Autowired
    ApiRequestLimitConfig apiRequestLimitConfig;


    private ConcurrentHashMap<Method, RateLimiter> limiterMap = new ConcurrentHashMap<>();



    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //HandlerMethod 封装方法定义相关的信息,如类,方法,参数等
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        System.out.println(method);
        // 获取方法中是否包含注解
        RequestLimit requestLimit = method.getAnnotation(RequestLimit.class);
        if (requestLimit != null) {
            if (isLimit(method, requestLimit)) {
                CommonResult<Object> result = CommonResult.error("110011", "限流了");
                response.getWriter().write(JSONObject.toJSON(result).toString());
                return false;
            }
        }
        return true;
    }

    /**
     * 是否限流
     * @param method
     * @param requestLimit
     * @return
     */
    public boolean isLimit(Method method, RequestLimit requestLimit) {
        RateLimiter rateLimiter = limiterMap.get(method);
        //nacos配置优先级最高
        Integer limit = apiRequestLimitConfig.getLimit().get(requestLimit.configKey());
        if(limit==null){
            limit = requestLimit.value();
        }
        if(rateLimiter == null){
            rateLimiter = RateLimiter.create(limit);
            limiterMap.putIfAbsent(method,rateLimiter);
        }else {
            if(limit!=rateLimiter.getRate()){
                synchronized (rateLimiter){
                    if(limit!=rateLimiter.getRate()){
                        rateLimiter.setRate(limit);
                    }
                }
            }
        }
        return !rateLimiter.tryAcquire();
    }




}
