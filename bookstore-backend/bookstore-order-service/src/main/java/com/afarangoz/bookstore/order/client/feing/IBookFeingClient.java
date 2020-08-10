package com.afarangoz.bookstore.order.client.feing;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.BOOKS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ID_PATH_VARIABLE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.afarangoz.bookstore.api.common.model.dto.BookDTO;


@FeignClient(name = "bookstore-book-service")
public interface IBookFeingClient {
	
	@GetMapping(SLASH_SYMBOL + BOOKS + ID_PATH_VARIABLE)
	public ResponseEntity<?> findById(@PathVariable Long id);
	
	@PutMapping(SLASH_SYMBOL + BOOKS + ID_PATH_VARIABLE)
	public ResponseEntity<?> update(@RequestBody BookDTO entityDTO, @PathVariable Long id);

}
