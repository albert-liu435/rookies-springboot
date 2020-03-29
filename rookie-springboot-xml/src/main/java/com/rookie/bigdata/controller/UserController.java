package com.rookie.bigdata.controller;

import com.rookie.bigdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description UserController
 * @Author
 * @Date 2020/3/27 9:01
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getuser")
    public String getUser(){
        userService.getUser();

        return "调用成功";
    }
}
