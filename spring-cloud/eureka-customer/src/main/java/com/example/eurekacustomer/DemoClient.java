//package com.example.eurekacustomer;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient("eureka-provider")
//public interface DemoClient {
//
//    @GetMapping("/dc")
//    String consumer();
//
//
//    @GetMapping("/dcSleep")
//    String dcSleep();
//
//
//    @GetMapping("/baggageTest")
//    String baggageTest();
//
//
//    @GetMapping("/getUserById")
//    String getUserById(@RequestParam String id);
//
//
//
//    @GetMapping("/testException")
//    String testException();
//
//
//    @RequestMapping("/testLog")
//    void testLog();
//}
