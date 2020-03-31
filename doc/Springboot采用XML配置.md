### Springboot采用XML配置

Springboot虽然推荐使用java配置代替XML配置，但是也提供了采用XML的配置。采用XMLp配置需要用到下面两个注解@Configuration和@ImportResource

##### @ImportResource注解

@ImportResource：通过locations属性或者value加载对应的xml配置文件，同时需要配合@Configuration注解一起使用，定义为配置类

使用方法如下

定义xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="com.rookie.bigdata.service.UserService"></bean>

</beans>
```

定义配置类

```java
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
```

定义service类

```java
package com.rookie.bigdata.service;

/**

 * @ClassName UserService
 * @Description UserService
 * @Author
 * @Date 2020/3/27 8:59
 * @Version 1.0
   */
   public class UserService {


    public UserService() {
    
    }
    
    public void getUser(){
        System.out.println("获取用户信息");
    }

}
```

定义controller

```java
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
```

运行成功后，调用http//localhost:8080/getuser即可查看运行效果

相关源码参考[github](https://github.com/albert-liu435/rookies-springboot/tree/master/rookie-springboot-xml)

