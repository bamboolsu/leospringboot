package com.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	}
}


/*
用apidoc命令生成文档界面
		先cd到工程的外层目录，并在外层目建个输出文档的目录，我建的是docapi。

		输命令：

		apidoc -i chapter4/ -o apidoc/

		-i 输入目录 -o 输出目录*/
