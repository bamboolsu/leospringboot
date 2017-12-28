# leospringboot
我的一个spring boot 测试例子


http://blog.csdn.net/bamboolsu/article/details/78913131

目录：
一， 第一个工程：

二，写一个HelloController

三，单元测试

四，配置文件的属性操作

五，环境切换：

六，操作数据库


简介

spring boot 它的设计目的就是为例简化开发，开启了各种自动装配，你不想写各种配置文件，引入相关的依赖就能迅速搭建起一个web工程。它采用的是建立生产就绪的应用程序观点，优先于配置的惯例。

可能你有很多理由不放弃SSM,SSH，但是当你一旦使用了spring boot ,你会觉得一切变得简单了，配置变的简单了、编码变的简单了，部署变的简单了，感觉自己健步如飞，开发速度大大提高了。就好比，当你用了IDEA，你会觉得再也回不到Eclipse时代一样。另，本系列教程全部用的IDEA作为开发工具。

spring boot 致力于简洁，让开发者写更少的配置，程序能够更快的运行和启动。它是下一代javaweb框架，并且它是spring cloud（微服务）的基础。


安装

springboot可以像标准库一样使用，只需引入spring-boot-*,但是通常一般使用maven构建工具
maven安装
一般采用Idea  IDE开发工具

一， 第一个工程：

采用idea.

具体步骤：

打开Idea-> new Project ->Spring Initializr ->填写group、artifact ->钩上web(开启web功能）->点下一步就行了

应用创建成功后，会生成相应的目录和文件。

自动生成application.properties,  
1， 需要手工修改为 application.yml
2，最好安装yaml插件
3， 添加内容如下：  这些都是约定的格式
server:
  port: 8080
  context-path: /springboot


自动生成SpringbootApplication.java 文件，  Application类,它是程序的入口:   内容如下：
package com.bamboolsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringbootApplication.class, args);
   }
}


自动生成的pom.xml 内容如下：（我选择了web， 所以自动包括了
spring-boot-starter-web
）
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
   <modelVersion>4.0.0</modelVersion>

   <groupId>com.bamboolsu</groupId>
   <artifactId>springboot</artifactId>
   <version>0.0.1-SNAPSHOT</version>
   <packaging>war</packaging>

   <name>springboot</name>
   <description>Demo project for Spring Boot</description>

   <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>1.5.9.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
   </parent>

   <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
      <java.version>1.8</java.version>
   </properties>

   <dependencies>
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
      </dependency>
   </dependencies>

   <build>
      <plugins>
         <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
         </plugin>
      </plugins>
   </build>


</project>

其中spring-boot-starter-web不仅包含spring-boot-starter,还自动开启了web功能


比如你引入了Thymeleaf的依赖，spring boot 就会自动帮你引入SpringTemplateEngine，当你引入了自己的SpringTemplateEngine，spring boot就不会帮你引入。它让你专注于你的自己的业务开发，而不是各种配置。



创建完工程，工程的目录结构如下：

- src
    -main
        -java
            -package
                -SpringbootApplication
        -resouces
            - statics
            - templates
            - application.yml
    -test
- pom

1
2
3
4
5
6
7
8
9
10
11
12
13
pom文件为基本的依赖管理文件
resouces 资源文件 
statics 静态资源
templates 模板资源
application.yml 配置文件
SpringbootApplication程序的入口。




二，写一个HelloController：
package com.bamboolsu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
        return "hi you!!!";
    }
    // http://localhost:8080/springboot/
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
} 
运行 Application的main(),程序会启动，

由于springboot自动内置了servlet容器，所以不需要类似传统的方式，先部署到容器再启动容器。
只需要运行main()即可，这时打开浏览器输入网址：localhost:8080/springboot/hi ，就可以在浏览器上看到: hi you!!!

启动SpringbootFirstApplication的main方法，打开浏览器localhost:8080/springboot/,浏览器显示：

Greetings from Spring Boot!

神奇之处：

你没有做任何的web.xml配置。
你没有做任何的sping mvc的配置; springboot为你做了。
你没有配置tomcat ;springboot内嵌tomcat.
启动springboot 方式

cd到项目主目录:

mvn clean  
mvn package  编译项目的jar
1
2
mvn spring-boot: run 启动
cd 到target目录，java -jar 项目.jar

来看看springboot在启动的时候为我们注入了哪些bean

在程序入口加入：
package com.bamboolsu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}

程序输出：
Let's inspect the beans provided by Spring Boot:
basicErrorController
beanNameHandlerMapping
beanNameViewResolver
boyController
boyProperties
boyRep
characterEncodingFilter
commandLineRunner
conventionErrorViewResolver
dataSource
dataSourceInitializedPublisher
dataSourceInitializer
dataSourceInitializerPostProcessor
defaultServletHandlerMapping
defaultValidator
defaultViewResolver
dispatcherServlet
dispatcherServletRegistration
duplicateServerPropertiesDetector
emBeanDefinitionRegistrarPostProcessor
embeddedServletContainerCustomizerBeanPostProcessor
entityManagerFactory
entityManagerFactoryBuilder
error
errorAttributes
errorPageCustomizer
errorPageRegistrarBeanPostProcessor
faviconHandlerMapping
faviconRequestHandler
handlerExceptionResolver
helloController
hiddenHttpMethodFilter
httpPutFormContentFilter
httpRequestHandlerAdapter
jacksonGeoModule
jacksonObjectMapper
jacksonObjectMapperBuilder
jdbcTemplate
jpaContext
jpaMappingContext
jpaVendorAdapter
jsonComponentModule
localeCharsetMappingsCustomizer
mappingJackson2HttpMessageConverter
mbeanExporter
mbeanServer
messageConverters
methodValidationPostProcessor
multipartConfigElement
multipartResolver
mvcContentNegotiationManager
mvcConversionService
mvcHandlerMappingIntrospector
mvcPathMatcher
mvcResourceUrlProvider
mvcUriComponentsContributor
mvcUrlPathHelper
mvcValidator
mvcViewResolver
namedParameterJdbcTemplate
objectNamingStrategy
openEntityManagerInViewInterceptor
org.springframework.aop.config.internalAutoProxyCreator
org.springframework.boot.autoconfigure.AutoConfigurationPackages
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration$JdkDynamicAutoProxyConfiguration
org.springframework.boot.autoconfigure.condition.BeanTypeRegistry
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$PooledDataSourceConfiguration
org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration$Tomcat
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration$DataSourceTransactionManagerConfiguration
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration
org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration$TomcatDataSourcePoolMetadataProviderConfiguration
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration$JpaWebConfiguration
org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration$JpaWebConfiguration$JpaWebMvcConfiguration
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration$CglibAutoProxyConfiguration
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$TransactionTemplateConfiguration
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletConfiguration
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration
org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration
org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration$EmbeddedTomcat
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration
org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration
org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration
org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration
org.springframework.boot.autoconfigure.web.JacksonHttpMessageConvertersConfiguration
org.springframework.boot.autoconfigure.web.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration
org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration
org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration
org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration
org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration$RestTemplateConfiguration
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$EnableWebMvcConfiguration
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$FaviconConfiguration
org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration$TomcatWebSocketConfiguration
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.store
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalPersistenceAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.event.internalEventListenerFactory
org.springframework.context.event.internalEventListenerProcessor
org.springframework.data.web.config.SpringDataJacksonConfiguration
org.springframework.data.web.config.SpringDataWebConfiguration
org.springframework.orm.jpa.SharedEntityManagerCreator#0
org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration
org.springframework.transaction.config.internalTransactionAdvisor
org.springframework.transaction.config.internalTransactionalEventListenerFactory
pageableResolver
persistenceExceptionTranslationPostProcessor
platformTransactionManagerCustomizers
preserveErrorControllerTargetClassPostProcessor
propertySourcesPlaceholderConfigurer
requestContextFilter
requestMappingHandlerAdapter
requestMappingHandlerMapping
resourceHandlerMapping
restTemplateBuilder
serverProperties
simpleControllerHandlerAdapter
sortResolver
spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
spring.http.encoding-org.springframework.boot.autoconfigure.web.HttpEncodingProperties
spring.http.multipart-org.springframework.boot.autoconfigure.web.MultipartProperties
spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties
spring.jpa-org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
spring.jta-org.springframework.boot.autoconfigure.transaction.jta.JtaProperties
spring.mvc-org.springframework.boot.autoconfigure.web.WebMvcProperties
spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties
spring.transaction-org.springframework.boot.autoconfigure.transaction.TransactionProperties
springApplicationAdminRegistrar
springbootApplication
standardJacksonObjectMapperBuilderCustomizer
stringHttpMessageConverter
tomcatEmbeddedServletContainerFactory
tomcatPoolDataSourceMetadataProvider
transactionAttributeSource
transactionInterceptor
transactionManager
transactionTemplate
viewControllerHandlerMapping
viewResolver
websocketContainerCustomizer
welcomePageHandlerMapping

在程序启动的时候，springboot自动诸如注入了40-50个bean.




三，单元测试

单元测试很重要， 可以测试各种接口
通过@RunWith() @SpringBootTest开启注解：
package com.bamboolsu;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {
    @LocalServerPort
    private int port;
    @Value("${server.context-path}")
    private String contextpath;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        // XXX here will setUp()  port is: 5373 contextpath is: /springboot
        System.out.println("XXX here will setUp()  port is: " + port + " contextpath is: " +  contextpath);
        this.base = new URL("http://localhost:" + port + "/" + contextpath + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        System.out.println("XXX here will getHello()   response.getBody is:" + response.getBody());
        assertThat(response.getBody(),equalTo("Greetings from Spring Boot!"));
    }
}

运行它会先开启sprigboot工程，然后再测试，测试通过





四，配置文件的属性操作
方法一：直接使用

application.yml 里添加下面内容
boy:
  name: bamboolsu
  age: 18
  content: content:${name},age:${age}

HelloController 类里面添加下面内容：
HelloController 

@Value("${boy.name}")
private String name;

//访问/hello或者/hi任何一个地址，都会返回一样的结果
@RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
public String say() {
    return "hi you!!!" +  name;
}

方法二： 通过ConfigurationProperties注解
将属性注入到bean中，通过Component注解将bean注解到spring容器中
package com.bamboolsu;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="boy")
@Component
public class BoyProperties {
    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

使用：
public class HelloController {

    @Value("${boy.name}")
    private String name;

    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    // 访问地址： http://localhost:8080/springboot/hi
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        return "hi you!!! name is: " + name + " age is: " + BoyProperties.getAge();
    }
}





五，环境切换：
比如开发环境， 测试环境， 线上环境等
application.yml 里面添加下面信息：
spring:
 profiles: active: prod
spring:
  profiles:
    active: prod

添加application-prod.yml 文件：
server:
  port: 8080
  context-path: /springboot
boy:
  name: bamboolsu_prod
  age: 18
  content: content:${name},age:${age}






六，操作数据库


采用JPA 操作数据库：
导入jar ，在pom.xml中添加依赖:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>

在appilication.yml中添加数据库配置：
datasource:
  driver-class-name: com.mysql.jdbc.Driver
  url: jdbc:mysql://localhost:3306/dbboy?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8
  username: root
  password: xxxx
jpa:
  hibernate:
    ddl-auto: update # create
  show-sql: true

这些都是数据库常见的一些配置没什么可说的，其中ddl_auto: create 代表在数据库创建表，update 代表更新，首次启动需要create ,如果你想通过hibernate 注解的方式创建数据库的表的话，之后需要改为 update.

创建一个实体boy，这是基于hibernate的:
package com.bamboolsu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Boy {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer age;

    public Boy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

创建Dao接口, springboot 将接口类会自动注解到spring容器中，
不需要做任何配置，只需要继承JpaRepository即可：
package com.bamboolsu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BoyRep extends JpaRepository<Boy,Integer> {
    //通过年龄查询
    public List<Boy> findByAge(Integer age);
}

创建一个BoyController，写一个获取所有girl的api和添加girl的api ，自己跑一下就可以了:
package com.bamboolsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoyController {
    @Autowired
    private BoyRep boyRep;

    /**
     * 查询所有列表
     * @return
     */
    @RequestMapping(value = "/boys",method = RequestMethod.GET)
    public List<Boy> getBoyList(){
        return boyRep.findAll();
    }

    // http://localhost:8080/springboot/boy
    @RequestMapping(value = "/boy",method = RequestMethod.GET)
    public String  getBoy(){
        String result;
        try {
            List<Boy> boys = boyRep.findAll();
            System.out.println("here will call boy! boys.size is: " + boys.size());
            result = "boy id is: " +  boys.get(0).getId() + " boy age is: " + boys.get(0).getAge();
        } catch (Exception e) {
            System.out.println("here exception, ex: " + e.getMessage());
            result = "here exception, ex: " + e.getMessage();
        }
        return result;
    }

    /**
     * 添加一个boy
     * @param age
     * @return
     */
    @RequestMapping(value = "/boys",method = RequestMethod.POST)
    public Boy addBoy(@RequestParam("age") Integer age){
        Boy boy = new Boy();
        boy.setAge(age);
        return boyRep.save(boy);
    }
}

如果需要事务的话，在service层加@Transaction注解即可。
