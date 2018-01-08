package com.bamboolsu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    @Value("${boy.name}")
    private String name;

    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    // 访问地址： http://localhost:8080/springboot/hi
    // 访问地址： http://localhost:8080/springboot/hello
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        System.out.println("hi you!!! name is: " + name + " age is: " + BoyProperties.getAge());
        return "hi you!!! name is: " + name + " age is: " + BoyProperties.getAge();
    }

    // http://localhost:8080/springboot/
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}