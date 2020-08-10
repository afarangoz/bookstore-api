package com.afarangoz.bookstore.book.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afarangoz.bookstore.api.common.model.entity.Book;
import com.afarangoz.bookstore.book.model.repository.IBookRepository;


@Service
@Transactional
public class BookService implements IBookService{

	@Autowired
	IBookRepository bookRepository;

	@Override
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public void delete(Book entity) {
		bookRepository.delete(entity);
	}

	@Override
	public Book createOrUpdate(Book entity) {
		return bookRepository.save(entity);
	}

}
