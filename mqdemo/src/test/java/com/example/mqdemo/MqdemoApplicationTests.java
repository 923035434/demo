package com.example.mqdemo;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MqdemoApplicationTests {


    @Test
    void contextLoads() {
        Random random = new Random();
        List<Map<String, String>> list = new ArrayList<>();
        for (int i =0;i<500;i++){
            HashMap<String, String> item = new HashMap<>();
           String tag =i%2==0?"A": "B";
            item.put("orderId",String.valueOf(i));
            item.put("price",String.valueOf(random.nextInt()));
            item.put("tag",tag);
            list.add(item);
        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
    }

}
