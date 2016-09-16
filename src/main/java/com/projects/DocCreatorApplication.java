package com.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.projects")
public class DocCreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocCreatorApplication.class, args);
	}
}
