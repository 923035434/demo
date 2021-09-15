//package com.example.eurekacustomer.controller;
//
//import brave.Span;
//import brave.Tracer;
//import brave.baggage.BaggageField;
//import brave.spring.web.TracingClientHttpRequestInterceptor;
//import com.example.eurekacustomer.DemoClient;
//import com.example.eurekacustomer.service.DemoService;
//import lombok.extern.slf4j.Slf4j;
//import lombok.var;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.cloud.sleuth.autoconfig.TraceEnvironmentPostProcessor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import javax.servlet.http.HttpServletRequest;
//
//
//@RestController
//@Slf4j
//public class DemoController {
//
//    @Autowired
//    LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    DemoClient client;
//
//    @Autowired
//    DemoService demoService;
//
//    @Value("${server.port}")
//    private String servicePort;
//
//
//    @Autowired
//    private Tracer tracer;
//
//
//    @GetMapping("/consumer")
//    public String dc() {
//        var newResTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client-demo");
//        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
//        log.info(url);
//        return newResTemplate.getForObject(url, String.class);
//    }
//
//
//
//
//
//    /**
//     * Ribbon + eurekaClient调用服务
//     * @return
//     */
//    @GetMapping("/consumer0")
//    public String dc0() {
//        return restTemplate.getForObject("http://eureka-client-demo/dc", String.class);
//    }
//
//    /**
//     * Feign + Ribbon + eurekaClient调用服务
//     * @return
//     */
//    @GetMapping("/consumer1")
//    public String dc1() {
//        return client.consumer();
//    }
//
//    /**
//     * Feign + Ribbon + eurekaClient调用服务(添加Hystrix服务降级)
//     * @return
//     */
//    @GetMapping("/consumer2")
//    public String dc2(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("TraceId={}, SpanId={} ,parentSpanId={}",
//                tracer.currentSpan().context().traceIdString(), tracer.currentSpan().context().spanIdString(), tracer.nextSpan().context().spanIdString());
//       return demoService.dc();
//    }
//
//
//
//    /**
//     * Feign + Ribbon + eurekaClient调用服务(添加Hystrix服务降级)（熔断）
//     * @return
//     */
//    @GetMapping("/consumer3")
//    public String dc3(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("TraceId={}, SpanId={} ,parentSpanId={}", tracer.currentSpan().context().traceIdString(), tracer.currentSpan().context().spanIdString(), tracer.nextSpan().context().spanIdString());
//        var result = demoService.dcSleep();
//        log.info("consumer3------------------------"+result);
//        return result;
//    }
//
//
//
//    //TracingClientHttpRequestInterceptor
//   // TraceEnvironmentPostProcessor
//
//
//    @GetMapping("/baggageTest")
//    public String baggageTest(@RequestParam("name") String name){
//        BaggageField field = BaggageField.getByName("name");
//        field.updateValue(name);
//        return demoService.baggageTest();
//    }
//
//
//
//
//    @GetMapping("/getUserById")
//    public String getUserById(@RequestParam String id) {
//        return demoService.getUserById(id);
//    }
//
//
//
//    @RequestMapping("/testLog")
//    public void testLog(){
//        demoService.testLog();
//    }
//
//
//    @GetMapping("/testException")
//    public String testException() {
//        return demoService.testException();
//    }
//
//
//    @RequestMapping("/customerSpan")
//    public void customerSpan() throws InterruptedException {
//        var newSpan = tracer.nextSpan().name("customer-span").start();
//        //耗时的操作
//        Thread.sleep(500l);
//        newSpan.tag("1", "2");
//        newSpan.finish();
//    }
//
//
//
//}
