package com.example.daily_push.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class DailyPushJob {



    private String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJkYXRhIjp7ImlkIjoxNDQyNjc5MjA3ODc1NDQ4ODM0LCJpc0RlbGV0ZWQiOjAsImNyZWF0ZWREYXRlIjoiMjAyMS0wOS0yOCAxMDozNTowMSIsImxhc3RNb2RpZnlEYXRlIjoiMjAyMS0wOS0yOCAxODozMjowNCIsInVzZXJOYW1lIjoi5YiY5YipIiwic3RhdHVzIjowLCJyZWFsTmFtZSI6IuWImOWIqSIsImVtYWlsIjoibGkubGl1QG1zeWMuY2MifSwiaXNzIjoib25pb24iLCJpYXQiOjE2NTA2MTI4MzksInN1YiI6IuWImOWIqSIsImV4cCI6MTY1MTMzMjgzOX0.tjwG-mSl4F_4IroGhnJgyHaj0xdXvEQz1c91jIhkt_c";

    private String content = "omall-plus相关开发";


//    每天晚上7点执行一次    0 0 19 * * ?
    @Scheduled(fixedDelay = 5000)
    public void doJob() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver_win32/chromedriver.exe");
//        System.out.println("doJob启动");
//        WebDriver driver = new ChromeDriver();    //Chrome浏览器
//        driver.get("https://www.baidu.cn");
//
//        driver.manage().window().maximize();
//        Thread.sleep(2000);
//
//        driver.get("https://m.baidu.cn");
//        driver.manage().window().setSize(new Dimension(480, 800));
//        Thread.sleep(2000);
//
//        driver.quit();
        RestTemplate resetTemplate = new RestTemplate();
        MultiValueMap<String, String> requestMap= new LinkedMultiValueMap<String, String>();
        requestMap.add("pageNum", "1");
        requestMap.add("pageSize", "1");
        requestMap.add("token", token);
        HttpEntity requestEntity = new HttpEntity(requestMap,null);
        String responseAsString = resetTemplate.postForObject("https://oniondaily.2beauti.com/daily/dailyRecord/getHistoryDaily",requestEntity,String.class);
        if(responseAsString!=null){
            JSONObject resultObj = JSON.parseObject(responseAsString);
            if((int)resultObj.get("code")==0){
               Object dataObj = resultObj.get("data");
               JSONObject data = (JSONObject) JSON.toJSON(dataObj);
                Object contentArrObj = data.get("content");
                JSONArray contentArr = JSON.parseArray(JSON.toJSONString(contentArrObj));
                Object contentObj = contentArr.get(0);
                JSONObject content = (JSONObject) JSON.toJSON(contentObj);
                LocalDateTime time = LocalDateTime.parse(content.get("createdDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if(time.toLocalDate().compareTo(LocalDate.now())!=0){
                    DayOfWeek week =LocalDate.now().getDayOfWeek();
                    if(week != DayOfWeek.SATURDAY && week != DayOfWeek.SUNDAY&&LocalTime.now().compareTo(LocalTime.of(18,30,0))>0) {
                        this.defaultDailyPush();
                    }
                }
            }
        }
        System.out.println(responseAsString);
    }



    public void defaultDailyPush(){
        RestTemplate resetTemplate = new RestTemplate();
        MultiValueMap<String, String> requestMap= new LinkedMultiValueMap<String, String>();
        requestMap.add("realName", "刘利");
        requestMap.add("projectId", "1391695299239612418");
        requestMap.add("actionCode", "1002");
        requestMap.add("actionName", "需求开发");
        requestMap.add("status", "2");
        requestMap.add("dailyType", "0");
        requestMap.add("dailyDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        requestMap.add("costTime", "7.5");
        requestMap.add("content", content);
        requestMap.add("parentProjectId", "1384108613102080002");
        requestMap.add("token", token);
        HttpEntity requestEntity = new HttpEntity(requestMap,null);
        String responseAsString = resetTemplate.postForObject("https://oniondaily.2beauti.com/daily/dailyRecord/commit",requestEntity,String.class);
        System.out.println(responseAsString);
    }




}
