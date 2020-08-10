package com.afarangoz.bookstore.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.afarangoz.bookstore.api.common.configuration.SwaggerConfig;
import com.afarangoz.bookstore.api.common.exception.handler.ErrorHandler;
import com.afarangoz.bookstore.api.common.model.service.file.FileHandlerServiceImpl;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.afarangoz.bookstore.api.common.model.entity"})
@Import(value= {FileHandlerServiceImpl.class, ErrorHandler.class, SwaggerConfig.class})
public class BookstoreBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreBookServiceApplication.class, args);
	}

}
