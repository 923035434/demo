package com.example.concurent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

@SpringBootApplication
public class ConcurentApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ConcurentApplication.class, args);

        /**
         * threadLocal的set就是把threadLocal当做key，把值set到线程对象里头的ThreadLocalMap中
         *
         */
//        ThreadLocalWrapper.name.set("12354");
//        ThreadLocalWrapper.name.get();
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(5,"5");
        map.put(1,"1");
        map.put(3,"3");
        map.put(4,"4");
        map.put(10,"10");
        map.put(2,"2");
        System.out.println(map.keySet());


//        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
//        map.put("1","1");
        //LockSupport.park(this);
        //AtomicMarkableReference
        //AtomicStampedReference
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4,10,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(20));
        pool.getPoolSize();
        //pool.execute();

    }

}
