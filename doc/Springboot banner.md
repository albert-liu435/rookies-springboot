Springboot banner

Banner的实现类如下：

![Banner](F:\IDEAWorkspace\github\rookie\rookie-springboots\rookies-springboot-resources\resources\Banner.png)

- SpringBootBanner：Banner的默认实现类
- ImageBanner：用来打印图片日志的banner
- ResourceBanner:资源Banner

Springboot在启动的时候，默认情况下会打印如下信息

```java
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.5.RELEASE)
```

可以分析源码看看为什么打印如下日志

```java
SpringApplication中run方法

public ConfigurableApplicationContext run(String... args) {
			...
			Banner printedBanner = printBanner(environment);
			....
		return context;
	}
```

调用printBanner(ConfigurableEnvironment environment)的方法

```java
private Banner printBanner(ConfigurableEnvironment environment) {
		if (this.bannerMode == Banner.Mode.OFF) {
			return null;
		}
    //获取资源加载
		ResourceLoader resourceLoader = (this.resourceLoader != null) ? this.resourceLoader
				: new DefaultResourceLoader(getClassLoader());
    //创建SpringApplicationBannerPrinter对象
		SpringApplicationBannerPrinter bannerPrinter = new SpringApplicationBannerPrinter(resourceLoader, this.banner);
		if (this.bannerMode == Mode.LOG) {
			return bannerPrinter.print(environment, this.mainApplicationClass, logger);
		}
    //打印日志
		return bannerPrinter.print(environment, this.mainApplicationClass, System.out);
	}
```

```java
Banner print(Environment environment, Class<?> sourceClass, PrintStream out) {
    	//获取Banner
		Banner banner = getBanner(environment);
    	//打印Banner
		banner.printBanner(environment, sourceClass, out);
		return new PrintedBanner(banner, sourceClass);
	}
```

getBanner(Environment environment)方法

```java
private Banner getBanner(Environment environment) {
	Banners banners = new Banners();
    //添加ImageBanner
	banners.addIfNotNull(getImageBanner(environment));
    //添加TextBanner
	banners.addIfNotNull(getTextBanner(environment));
	if (banners.hasAtLeastOneBanner()) {
		return banners;
	}
	if (this.fallbackBanner != null) {
		return this.fallbackBanner;
	}
	return DEFAULT_BANNER;
}
```
```java
static final String BANNER_IMAGE_LOCATION_PROPERTY = "spring.banner.image.location";
static final String[] IMAGE_EXTENSION = { "gif", "jpg", "png" };
private Banner getImageBanner(Environment environment) {
		String location = environment.getProperty(BANNER_IMAGE_LOCATION_PROPERTY);
		if (StringUtils.hasLength(location)) {
			Resource resource = this.resourceLoader.getResource(location);
			return resource.exists() ? new ImageBanner(resource) : null;
		}
    //获取banner.gif，banner.jpg,banner.png
		for (String ext : IMAGE_EXTENSION) {
			Resource resource = this.resourceLoader.getResource("banner." + ext);
			if (resource.exists()) {
				return new ImageBanner(resource);
			}
		}
		return null;
	}
```

```java
	static final String BANNER_LOCATION_PROPERTY = "spring.banner.location";
	static final String DEFAULT_BANNER_LOCATION = "banner.txt";
private Banner getTextBanner(Environment environment) {
    //获取路径下的banner.txt;
		String location = environment.getProperty(BANNER_LOCATION_PROPERTY, DEFAULT_BANNER_LOCATION);
		Resource resource = this.resourceLoader.getResource(location);
		if (resource.exists()) {
			return new ResourceBanner(resource);
		}
		return null;
	}
```

通过上面的代码可以知道，如果在classpath路径下面添加了banner.txt,banner.jpg文件后，首先加载banner.jpg，然后加载banner.txt文件，后面都会打印出来。

下面通实现自己的Banner

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class,args);
    
        SpringApplication application=new SpringApplication(Application.class);
        //关闭banner
       // application.setBannerMode(Banner.Mode.OFF);
        application.setBanner(new Mybanner());
        application.run(args);

//        SpringApplicationBuilder applicationBuilder=new SpringApplicationBuilder();
//        //applicationBuilder.profiles("");
//        applicationBuilder.bannerMode(Banner.Mode.OFF).run(args);

    }

}
```

```java
public class Mybanner implements Banner {

    private static final String BANNER = "Spring Mybanner";
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
    
        System.out.println(BANNER);
    }

}
```

 [https://github.com/albert-liu435/rookies-springboot]( https://github.com/albert-liu435/rookies-springboot ) 





