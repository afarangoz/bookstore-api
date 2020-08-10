package com.afarangoz.bookstore.api.common.util;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.COLON_WITH_SPACE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ERRORS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.MESSAGE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtil {

	private ApiUtil() {

	}

	public static ResponseEntity<Map<String, String>> getNotFoundResponseEntity() {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<Map<String, String>> getBadRequestResponseEntity(String message) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, message);
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<Map<String, List<String>>> getResponseEntity(BindingResult bindingResult) {
		Map<String, List<String>> responseMap = new HashMap<>();
		List<String> erros = bindingResult.getFieldErrors().stream()
				.map(error -> error.getField() + COLON_WITH_SPACE + error.getDefaultMessage())
				.collect(Collectors.toList());
		responseMap.put(ERRORS, erros);
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}

	public static <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper.readValue(json, clazz);
	}

	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
