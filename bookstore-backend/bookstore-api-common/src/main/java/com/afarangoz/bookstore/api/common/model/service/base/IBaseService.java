package com.afarangoz.bookstore.api.common.model.service.base;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<T, ID> {

	public Page<T> findAll(Pageable pageable);
	
	public Optional<T> findById(ID id);
	
	public void delete(T entity);
	
	public T createOrUpdate(T entity);
}
