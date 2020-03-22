SpringBoot run()方法解析

SpringBoot在启动的时候，最终调用的是SpringApplication中的如下方法

```java
public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
	return new SpringApplication(primarySources).run(args);
}
```
new SpringApplication(primarySources)的源码如下：

创建SpringApplication对象

```java
public SpringApplication(Class<?>... primarySources) {
		this(null, primarySources);
	}
	
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    	//获取资源加载器
		this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    //确定Web应用启动容器的类型，默认情况下为SERVLET
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
    //实例化并加载所有可以加载的ApplicationContextInitializer
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    //实例化并加载所有可以加载的ApplicationListener
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    //查找main方法的类对象
		this.mainApplicationClass = deduceMainApplicationClass();
	}
```

setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class))和setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));的调用实例如下

```java
//根据给定的Class获取实例
	private <T> Collection<T> getSpringFactoriesInstances(Class<T> type) {
		return getSpringFactoriesInstances(type, new Class<?>[] {});
	}

	private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
		ClassLoader classLoader = getClassLoader();
		// Use names and ensure unique to protect against duplicates
		Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
		//创建实例
		List<T> instances = createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
		//对实例进行排序
		AnnotationAwareOrderComparator.sort(instances);
		return instances;
	}


```

下面就开始调用run方法

```java
/**
	 * 运行Spring Application，c创建并刷新ApplicationContext对象
	 * {@link ApplicationContext}.
	 * @param args the application arguments (usually passed from a Java main method)
	 * @return a running {@link ApplicationContext}
	 */
public ConfigurableApplicationContext run(String... args) {
    //用来统计总的线程的执行时间和当前线程的执行时间
	StopWatch stopWatch = new StopWatch();
	stopWatch.start();
    //初始化应用上下文和异常报告集合
	ConfigurableApplicationContext context = null;
	Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
	//设置java.awt.headless的值，默认为true
    configureHeadlessProperty();
    //创建所有 Spring 运行监听器并发布应用启动事件
	SpringApplicationRunListeners listeners = getRunListeners(args);
    //启动各个SpringApplicationRunListener 监听器实例
	listeners.starting();
	try {
        //初始化默认参数
		ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        //根据运行监听器和应用参数来准备 Spring 环境
		ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
		configureIgnoreBeanInfo(environment);
        //创建Banner并打印banner的相关信息
		Banner printedBanner = printBanner(environment);
        //创建应用 上下文信息
		context = createApplicationContext();
        //异常报告
		exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
				new Class[] { ConfigurableApplicationContext.class }, context);
        //准备应用上下文信息
		prepareContext(context, environment, listeners, applicationArguments, printedBanner);
        //刷新应用下上文
		refreshContext(context);
        //应用上下文刷新后置处理
		afterRefresh(context, applicationArguments);
		stopWatch.stop();
        //输出日志记录执行的主类名等信息
		if (this.logStartupInfo) {
			new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
		}
        //发布应用上下文信息
		listeners.started(context);
        //执行所有 Runner 运行器
		callRunners(context, applicationArguments);
	}
	catch (Throwable ex) {
		handleRunFailure(context, ex, exceptionReporters, listeners);
		throw new IllegalStateException(ex);
	}

	try {
		listeners.running(context);
	}
	catch (Throwable ex) {
		handleRunFailure(context, ex, exceptionReporters, null);
		throw new IllegalStateException(ex);
	}
	return context;
}
```
getRunListeners(args)获取监听器

```java
private SpringApplicationRunListeners getRunListeners(String[] args) {
	Class<?>[] types = new Class<?>[] { SpringApplication.class, String[].class };
	return new SpringApplicationRunListeners(logger,
			getSpringFactoriesInstances(SpringApplicationRunListener.class, types, this, args));
}
```


