package com.ll.generateddemo;


import com.ll.generateddemo.dao.mapper.UserMapper;
import com.ll.generateddemo.dao.model.User;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;


//@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DaoTest {

    @Autowired
    UserMapper userMapper;


    @Test
    public void insert(){
        for (var i=0;i<100;i++){
            Integer temp = i+1;
            User user = new User(){{
                setName("小明"+ temp);
                setGender(1);
                setPhone("155446843");
                setWxCode("351a5d64w5ed4w");
                setMessage("今天心情不错");
                setBirthday(LocalDate.now());
            }};
            userMapper.insert(user);
        }
    }

    @Test
    public void updateByPrimaryKey(){
        User user = new User(){{
            setId(7l);
            setName("小刚");
            setGender(1);
            setPhone("155446843");
            setWxCode("351a5d64w5ed4w");
            setMessage("今天心情不错");
            setBirthday(LocalDate.now());
        }};
        userMapper.updateByPrimaryKey(user);
    }


    @Test
    public void selectByPrimaryKey(){
       var user = userMapper.selectByPrimaryKey(7l);
       System.out.println(user);
    }

    @Test
    public void selectListByPrimaryKeys(){
        var ids = new ArrayList<Long>(){{
            add(9l);
        }};
        var user = userMapper.selectListByPrimaryKeys(ids);
        System.out.println(user);
    }


    @Test
    public void deleteByPrimaryKey(){
        var result = userMapper.deleteByPrimaryKey(7l);
        System.out.println(result);
    }

    @Test
    public void pageList(){
        var result = userMapper.pageList(2,10);
        System.out.println(result);
    }


}
