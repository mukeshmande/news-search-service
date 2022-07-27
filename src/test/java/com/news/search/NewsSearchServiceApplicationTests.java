package com.news.search;

import com.news.search.controllers.NewsSearchController;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NewsSearchServiceApplicationTests {

	@Autowired
	private NewsSearchController newsSearchController;


	@Test
	void contextLoads() {
        assertThat(newsSearchController).isNotNull();
	}

}
