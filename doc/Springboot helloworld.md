Springboot helloworld

 自己使用springboot也已经写过一段时间的代码，但是对springboot真正运行的流程还是有点模糊，今天写出自己对springboot的认识，如有不对，还请各位大佬不吝赐教 .

代码如下：

```java
Springboot启动类

package com.rookie.bigdata;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
```

HelloController

```java
package com.rookie.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

/**

*@author rookie

*@version 1.0

*@date 2020/3/7 19:48
*/
@RestController
public class HelloController {

@GetMapping ("/hello")
public String hello(){
    return "hello world";
}
}
```

启动Springboot之后，直接在浏览器中访问http://localhost:8080/hello即可

Springboot启动类中有一个注解，即@SpringBootApplication，它的作用同 @Configuration,@EnableAutoConfiguration,@ComponentScan 三个注解一样。即

@SpringBootApplication=@Configuration+@EnableAutoConfiguration+@ComponentScan

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {

	@AliasFor(annotation = EnableAutoConfiguration.class)
	Class<?>[] exclude() default {};
	
	@AliasFor(annotation = EnableAutoConfiguration.class)
	String[] excludeName() default {};
	
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	String[] scanBasePackages() default {};
	
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
	Class<?>[] scanBasePackageClasses() default {};
	
	@AliasFor(annotation = Configuration.class)
	boolean proxyBeanMethods() default true;

}
```

##### @Inherited的作用

类继承关系中@Inherited的作用
类继承关系中，子类会继承父类使用的注解中被@Inherited修饰的注解
接口继承关系中@Inherited的作用
接口继承关系中，子接口不会继承父接口中的任何注解，不管父接口中使用的注解有没有被@Inherited修饰
类实现接口关系中@Inherited的作用
类实现接口时不会继承任何接口中定义的注解

##### @SpringBootConfiguration的作用

@SpringBootConfiguration的作用和@Configuration的作用一样，标注当前类为一个配置类，用来指示一个类声明一个或多个@Bean的方法，并交给Spring容器进行管理。

如代码

```java
@Configuration
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
```

##### @EnableAutoConfiguration作用

 通知SpringBoot开启自动配置功能，这样自动配置才能生效。源码如下： 

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
//根据条件来注册我们需要的bean实例，@Import注解支持导入普通的java类,并将其声明成一个bean.给容器中导入组件
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```

 @AutoConfigurationPackage 自动配置包注解，源码如下： 

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//@Import(AutoConfigurationPackages.Registrar.class)：默认将主配置类(@SpringBootApplication)所在的包及其子包里面的所有组件扫描到Spring容器中。
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
```



dsf







