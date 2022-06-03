package com.example.proxy.jdktest;

import com.example.proxy.cglibtest.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {




    public static void main(String[] args){
        final User user = User.builder().name("刘利").build();
        UserService userService = (UserService) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("sayHello")){
                    System.out.println("你好：");
                    System.out.println("我的名字叫"+((User)args[0]).getName());
                }
                return null;
            }
        });
        userService.sayHello(user);
    }

}
