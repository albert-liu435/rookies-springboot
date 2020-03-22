#### Springboot的配置文件

##### Springboot application.properties配置文件

springboot加载配置文件的顺序

- file:./config/

- file:/

- classpath:./config/

- classpath:/

  Springboot加载的时候会把上面路径的配置文件全部加载进去，加载的优先级由下往上依次降低，即首先加载classpath:/下的配置文件，然后加载classpath:./config/下面的配置文件，后加载的配置文件设置的属性值会覆盖先加载的配置文件设置的属性。

  application.yml配置文件同上面的加载顺序一样，当在相同的路径下存在application.yml和application.properties时，application.yml会覆盖application.properites的属性值。

  当不想使用applicaiton.properties配置文件时，可以通过spring.config.name来设置文件名称。

##### 自定义配置文件

使用自定义配置文件会用到@ConfigurationProperties和@PropertySource两个注解

@ConfigurationProperties：将配置文件中的配置以属性的形式自动注入到实体中， ignoreUnknownFields默认为ture, 自动检查配置文件中的属性是否存在，不存在则在启动时就报错。

@PropertySource:用来指定加载指定的配置文件 

如下自定义代码

```java
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
```

user.properties配置信息

```properties
spring.user.name=zhangsan
spring.user.address=beijing
```

这样就把配置文件中的属性值注入到了User对象中 ，下面是测试代码

```java
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
```

相关源码可以参考 [githube]( https://github.com/albert-liu435/rookies-springboot/tree/master/rookie-springboot-properties ) 

