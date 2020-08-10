package com.afarangoz.bookstore.api.common;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.afarangoz.bookstore.api.common.exception.handler.ErrorHandler;
import com.afarangoz.bookstore.api.common.model.service.file.FileHandlerServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Import(value= {FileHandlerServiceImpl.class, ErrorHandler.class})
public class BookstoreApiCommonApplication {

}
