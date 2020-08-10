package com.afarangoz.bookstore.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import com.afarangoz.bookstore.api.common.exception.handler.ErrorHandler;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.afarangoz.bookstore.api.common.model.entity"})
@Import(value= {ErrorHandler.class})
public class BookstoreUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreUserServiceApplication.class, args);
	}

}
