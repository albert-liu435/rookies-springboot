package com.rookie.bigdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/8 14:33
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {


        return "hello world";

    }
}