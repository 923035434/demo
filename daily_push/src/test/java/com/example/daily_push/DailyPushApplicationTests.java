package com.example.daily_push;

import com.alibaba.fastjson.JSON;
import com.example.daily_push.job.DailyPushJob;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@SpringBootTest
class DailyPushApplicationTests {

    @Autowired
    DailyPushJob dailyPushJob;

    @Test
    void contextLoads() throws InterruptedException {
//        dailyPushJob.doJob();
        String memberCode = "b";
        char lastChar = memberCode.toLowerCase().toCharArray()[memberCode.length()-1];
        Integer abValue = Integer.valueOf(lastChar)%2;
        System.out.println(Integer.valueOf(lastChar));
    }



    @Test
    void test1() {
    }


    @Test
    public void countDownLatch(){
        //总数是6 必须要执行的任何的时候，再使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号小朋友回来了");
                countDownLatch.countDown();// 数量-1
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();//等待计数器归 0  ，然后再向下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关门");
    }


    @Test
    public void cyclickBarrier() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("开跑！");
        });
        for (int i = 1; i <= 7 ; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"选手准备好了");
                try {
                    cyclicBarrier.await();//等待
                    System.out.println(Thread.currentThread().getName()+"选手冲向了终点");
                } catch(Exception e){
                    e.printStackTrace();
                }
            }).start();

        }

        Thread.sleep(5000);
    }


    @Test
    public void semaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 3; i++) {
            new Thread(()->{
                //acquire() 得到
                try {
                    semaphore.acquire();//抢到车位
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //release() 释放
                    semaphore.release();//释放
                }
            },String.valueOf(i)).start();
        }
        Thread.sleep(20000);
    }




}
