package com.example.eureka.provider.controller;



import brave.Tracer;
import brave.baggage.BaggageField;
import com.example.eureka.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
public class DemoController {

    @Autowired
    DiscoveryClient discoveryClient;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Tracer tracer;


    @Value("${server.port}")
    private String servicePort;

    @Autowired
    UserService userService;

    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        //Thread.sleep(4000L);
        String services = "Services-"+servicePort+": " + discoveryClient.getServices();
        System.out.println(services);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("TraceId={}, SpanId={} ,parentSpanId={}",
                tracer.currentSpan().context().traceIdString(), tracer.currentSpan().context().spanIdString(), tracer.currentSpan().context().parentIdString());
        logger.info("TraceId={}, SpanId={},parentSpanId = {} ",
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"),request.getHeader("X-B3-ParentsSpanId"));
        return services;
    }


    @GetMapping("/dcSleep")
    public String dcSleep() throws InterruptedException {
        Thread.sleep(3000L);
        String services = "Services-"+servicePort+": " + discoveryClient.getServices();
        return services;
    }


    @GetMapping("/newSpan")
    @NewSpan("newSpan")
    public String newSpan(@SpanTag("id")@RequestParam String id) {
        var result = "id:"+id;
        log.info(result);
        return result;
    }

    @GetMapping("/ContinueSpan")
    @ContinueSpan
    public String ContinueSpan(@SpanTag("id")@RequestParam String id) {
        var result = "id:"+id;
        log.info(result);
        return result;
    }


    @GetMapping("/baggageTest")
    public String baggageTest() {
        BaggageField field = BaggageField.getByName("name");
//        tracer.currentSpan().tag("name",field.getValue());
     return "baggageValue:"+field.getValue();
    }

    @RequestMapping("/getUserById")
    public String getUserById(@RequestParam String id) {
        return userService.getUserById(id).getName();
    }


    @RequestMapping("/testException")
    public String testException() throws Exception {
        throw new Exception("手动出错");
    }

    @RequestMapping("/testLog")
    public void testLog(){
        log.info("测试log：info");
        log.warn("测试log：warn");
        log.error("测试log：error");
    }


}
