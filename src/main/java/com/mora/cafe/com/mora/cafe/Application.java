package com.mora.cafe.com.mora.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.mora.cafe.com.mora.cafe.repo", "com.mora.cafe.com.mora.cafe.dao", "com.mora.cafe.com.mora.cafe.serviceImpl",  "com.mora.cafe.com.mora.cafe.security", "com.mora.cafe.com.mora.cafe.security.jwt"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
