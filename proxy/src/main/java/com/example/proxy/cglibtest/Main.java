package com.example.proxy.cglibtest;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Main {


    //https://www.yuque.com/renyong-jmovm/ds/bnfwbc
    public static void main(String[] args){
        final User user = User.builder().name("刘利").build();
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("sayHello")){
                    System.out.println("你好：");
                }
                return methodProxy.invokeSuper(o,objects);
            }
        });
        //设置需要增强的类
        enhancer.setSuperclass(UserService.class);
        //创建代理对象
        //底层用的asm写字节码技术生成class的字节码   org.springframework.asm.ClassWriter
        //可配置idea生成 cglib代理类到target文件夹中。
        UserService proxy = (UserService) enhancer.create();
        proxy.sayHello(user);
    }



}
