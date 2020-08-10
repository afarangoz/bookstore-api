package com.afarangoz.bookstore.api.common.exception.handler;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.EXCEPTION;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.MESSAGE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.NULL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.UNEXPECTED_ERROR;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Class ErrorHandler.
 */
@ControllerAdvice
public class ErrorHandler {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    /**
     * Exception handler.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
    	Map<String, String> responseMap = new HashMap<>();
    	logger.error(EXCEPTION, e);
    	if(e.getMessage() == null || e.getMessage().trim().isEmpty() || e.getMessage().equals(NULL)) {
    		responseMap.put(MESSAGE, UNEXPECTED_ERROR);
    	}else {
    		responseMap.put(MESSAGE, e.getMessage());
    	}
    	
    	
		return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
