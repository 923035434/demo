package com.example.springcloud;

import com.example.springcloud.model.ClassInfo;
import com.example.springcloud.model.UserInfo;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;


@SpringBootTest
class RedissonApplicationTests {


	@Autowired
	RedissonClient redissonClient;



	@Test
	public void testString(){
		RBucket<UserInfo> bucket = redissonClient.getBucket("xiaoming");
		var userInfo = new UserInfo();
		userInfo.setAge(13);
		userInfo.setName("小明");
		userInfo.setGender("男");
		bucket.set(userInfo);
		UserInfo user = bucket.get();
		System.out.println(user);
	}

	@Test
	public void testBitSet(){
		RBitSet set = redissonClient.getBitSet("simpleBitset");
		set.set(0, true);
		set.set(2,true);
		set.set(18,true);
		var size = set.cardinality();
		System.out.println(size);
	}

	@Test
	public void atomicLong() throws InterruptedException {
		RAtomicLong atomicLong = redissonClient.getAtomicLong("myAtomicLong");
		atomicLong.set(0);
		var cyb = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {
				System.out.println(atomicLong.get());
				atomicLong.delete();
			}
		});
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				for (var i=0;i<100;i++){
					atomicLong.incrementAndGet();
				}
				try {
					cyb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				for (var i=0;i<100;i++){
					atomicLong.incrementAndGet();
				}
				try {
					cyb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(500);
	}




	@Test
	public void lockTest() throws InterruptedException {

		final Integer[] value = {0};
		value[0] = 0;
		var thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<100;i++){
					RLock lock = redissonClient.getLock("anyLock");
					// 最常见的使用方法
					lock.lock();
					value[0] = value[0] +1;
					lock.unlock();
				}
			}
		});


		var thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<100;i++){
					RLock lock = redissonClient.getLock("anyLock");
					// 最常见的使用方法
					lock.lock();
					value[0] = value[0] +1;
					lock.unlock();
				}
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(5000);
		System.out.println(value[0]);

	}


	@Test
	public void steamTest() {
		var user1 = new UserInfo(){{
			setGender("女");
			setName("小红");
			setAge(10);
		}};
		var user2 = new UserInfo(){{
			setGender("男");
			setName("小明");
			setAge(10);
		}};
		var user3 = new UserInfo(){{
			setGender("男");
			setName("小刚");
			setAge(10);
		}};
		var user4 = new UserInfo(){{
			setGender("男");
			setName("小唐");
			setAge(10);
		}};
		var class1 = new ClassInfo(){{
			setUserInfo(new ArrayList<UserInfo>(){{
				add(user1);
				add(user2);
			}});
		}};
		var class2 = new ClassInfo(){{
			setUserInfo(new ArrayList<UserInfo>(){{
				add(user3);
				add(user4);
			}});
		}};
		var classList = new ArrayList<ClassInfo>(){{
			add(class1);
			add(class2);
		}};
		var list = classList.stream().map(c->c.getUserInfo().stream().map(u->u.getName()).collect(Collectors.toList())).collect(Collectors.reducing((r1,r2)->{
			r1.addAll(r2);
			return r1;
		})).get().stream().distinct().collect(Collectors.toList());
		System.out.println(list);

	}


}
