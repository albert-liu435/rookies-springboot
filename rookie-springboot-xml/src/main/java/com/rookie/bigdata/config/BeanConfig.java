package com.rookie.bigdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName Config
 * @Description Config
 * @Author
 * @Date 2020/3/26 9:30
 * @Version 1.0
 */
@Configuration
//@ImportResource("classpath:/bean.xml")
//@ImportResource(locations = {"classpath:bean.xml"})
@ImportResource(value = "classpath:/bean.xml")
public class BeanConfig {
}
