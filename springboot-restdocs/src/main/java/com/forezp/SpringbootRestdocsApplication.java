package com.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRestdocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestdocsApplication.class, args);
	}

	//这时只需要通过mvnw package命令就可以生成文档了。

}
