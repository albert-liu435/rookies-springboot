package com.rookie.bigdata.config;



import com.rookie.bigdata.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/7 21:09
 */
@SpringBootConfiguration
//@Configuration
@PropertySource("classpath:/config/app.properties")
public class AppConfig {

    @Value("${name}")
    private String name;
    /**
     * 生成一个Book对象，并将其交由Spring容器管理
     * @return
     */
    @Bean
    public Book myBook(){
        Book book=new Book();
        book.setAuthor("张三");
        book.setName(name);
        return book;
    }
}
