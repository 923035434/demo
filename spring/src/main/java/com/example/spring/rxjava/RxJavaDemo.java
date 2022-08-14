package com.example.spring.rxjava;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RxJavaDemo {


    /**
     * Single 类表示单值响应。单个 observable 只能发出单个成功值或错误。它不会发出 onComplete 事件。
     */
    public void single() throws InterruptedException {
        Single<String> testSingle = Single.just("啦啦啦啦");
        CountDownLatch latch = new CountDownLatch(1);
        Disposable disposable = testSingle.delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(@NonNull String s) {
                        System.out.println(s);
                        latch.countDown();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        latch.countDown();
                    }
                });
        latch.await();
        disposable.dispose();
    }


    public void maybe() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Disposable disposable = Maybe.just("你好啊")
                .delay(1,TimeUnit.SECONDS,Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<String>() {
                    @Override
                    public void onSuccess(@NonNull String s) {
                        System.out.println(s);
                        latch.countDown();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.getStackTrace());
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                        latch.countDown();
                    }
                });
        latch.await();
        disposable.dispose();
    }












}
