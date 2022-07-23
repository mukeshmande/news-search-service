package com.news.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
public class NewsSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSearchServiceApplication.class, args);
	}

}
