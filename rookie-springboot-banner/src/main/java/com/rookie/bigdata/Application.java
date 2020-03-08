package com.rookie.bigdata;


import com.rookie.bigdata.banner.Mybanner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class,args);

        SpringApplication application=new SpringApplication(Application.class);
       // application.setBannerMode(Banner.Mode.OFF);
        application.setBanner(new Mybanner());
        application.run(args);

//        SpringApplicationBuilder applicationBuilder=new SpringApplicationBuilder();
//        //applicationBuilder.profiles("");
//        applicationBuilder.bannerMode(Banner.Mode.OFF).run(args);

    }
}
