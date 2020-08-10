package com.afarangoz.bookstore.api.common.controller.base;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IBaseController <DTO, ID> {

	public ResponseEntity<?> findAll(int number, int size);
	
	public ResponseEntity<?> findById(ID id);
	
	public ResponseEntity<?>delete(ID id);
	
	public ResponseEntity<?> create(DTO entityDTO, BindingResult bindingResult);
	
	public ResponseEntity<?> update(DTO entityDTO, BindingResult bindingResult, ID id);
}
