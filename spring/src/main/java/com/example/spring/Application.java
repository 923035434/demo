package com.example.spring;

import com.example.spring.rxjava.RxJavaDemo;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
public class Application {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        RxJavaDemo rxJavaDemo = new RxJavaDemo();
        //rxJavaDemo.single();
//        rxJavaDemo.maybe();
        abcDemo();
    }


    volatile static int state =1;


    public static void abcDemo(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 4, 500, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        int num = 10;
        int sumNum = 3*num;
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    if(state<=sumNum){
                        if(state%3==1){
                            System.out.println("A");
                            System.out.println(Thread.currentThread().getId());
                            state++;
                        }
                    }else {
                        System.out.println("A OUT");
                        break;
                    }
                    Thread.yield();
                }
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    if(state<=sumNum){
                        if(state%3==2){
                            System.out.println("B");
                            System.out.println(Thread.currentThread().getId());
                            state++;
                        }
                    }else {
                        System.out.println("B OUT");
                        break;
                    }
                    Thread.yield();
                }
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    if(state<=sumNum){
                        if(state%3==0){
                            System.out.println("C");
                            System.out.println(Thread.currentThread().getId());
                            state++;
                        }
                    }else {
                        System.out.println("C OUT");
                        break;
                    }
                    Thread.yield();
                }
            }
        });

    }
}
