package com.rookie.bigdata.controller;



import com.rookie.bigdata.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/7 19:48
 */
@RestController
public class HelloController {

    @Autowired
    private Book book;

    @GetMapping ("/hello")
    public String hello(){
        System.out.println(book.getAuthor());
        System.out.println(book.getName());
        System.out.println("总共");
        //return "hello world";
        return book.getName();
    }
}
