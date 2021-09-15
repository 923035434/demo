//package com.example.eurekacustomer.service;
//
//import com.example.eurekacustomer.DemoClient;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Service
//
//public class DemoService {
//    @Autowired
//    DemoClient client;
//
//
//    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000" )})
//    public String dc() {
//        //Thread.sleep(4000L);
//        return client.consumer();
//    }
//
//
//    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000" )})
//    public String dcSleep() {
//        return client.dcSleep();
//    }
//
//
//    public String fallback() {
//        return "fallback";
//    }
//
//
//    public String baggageTest(){
//        return client.baggageTest();
//    }
//
//
//    public String getUserById(String id){
//        return client.getUserById(id);
//    }
//
//
//    public String testException(){
//        return client.testException();
//    }
//
//
//    public void testLog(){
//        client.testLog();
//    }
//}
