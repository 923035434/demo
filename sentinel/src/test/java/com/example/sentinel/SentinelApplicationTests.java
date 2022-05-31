package com.example.sentinel;

import lombok.Data;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.stream.Collectors;


@SpringBootTest
class SentinelApplicationTests {


	@Test
	public void test1(){
		var list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		for (var i=0;i<10000;i++){
			var list1 = list.stream().filter(l->l.equals("1")).collect(Collectors.toList());
			var list2 = list.stream().filter(l->!l.equals("1")).collect(Collectors.toList());
			var allList = list2.addAll(list1);
			System.out.println(allList);
		}

	}

	@Test
	public void test2(){
		String str = "正        		品";
		str = str.replaceAll("\\s*", "");
		System.out.println(str);
	}


}
