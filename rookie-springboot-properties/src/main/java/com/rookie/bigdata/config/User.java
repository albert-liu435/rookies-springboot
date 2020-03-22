package com.rookie.bigdata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/22 19:15
 */
@Configuration
@ConfigurationProperties(prefix = "spring.user")
@PropertySource("classpath:user.properties")
public class User {

    private String name;
    private String address;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
