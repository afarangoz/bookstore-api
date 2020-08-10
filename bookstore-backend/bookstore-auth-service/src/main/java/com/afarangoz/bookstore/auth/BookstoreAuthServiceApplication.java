package com.afarangoz.bookstore.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.afarangoz.bookstore.api.common.exception.handler.ErrorHandler;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Import(value= {ErrorHandler.class})
public class BookstoreAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAuthServiceApplication.class, args);
	}

}
