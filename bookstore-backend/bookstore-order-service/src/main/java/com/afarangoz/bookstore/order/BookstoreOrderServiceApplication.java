package com.afarangoz.bookstore.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.afarangoz.bookstore.api.common.configuration.SwaggerConfig;
import com.afarangoz.bookstore.api.common.exception.handler.ErrorHandler;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan({"com.afarangoz.bookstore.api.common.model.entity"})
@Import(value= {ErrorHandler.class, SwaggerConfig.class})
public class BookstoreOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreOrderServiceApplication.class, args);
	}

}
