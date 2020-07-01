package com.softcrud.TestPipeLine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestPipeLineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPipeLineApplication.class, args);
	}

}
