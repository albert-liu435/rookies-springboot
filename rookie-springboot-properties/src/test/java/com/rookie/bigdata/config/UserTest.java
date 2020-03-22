package com.rookie.bigdata.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/22 19:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private User user;

    @Test
    public void test1(){
        System.out.println(user.getName());
        System.out.println(user.getAddress());
    }

}
