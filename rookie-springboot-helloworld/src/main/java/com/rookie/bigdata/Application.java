package com.rookie.bigdata;


import com.rookie.bigdata.domain.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

//        SpringApplication.run(Application.class, args);
//        SpringApplication application = new SpringApplication(Application.class);
//        ConfigurableApplicationContext context = application.run(args);
//        Book book = (Book) context.getBean("book");
//        System.out.println(book);
    }
}
