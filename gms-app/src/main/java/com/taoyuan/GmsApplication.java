package com.taoyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmsApplication.class, args);
	}
}
