package com.afarangoz.bookstore.book.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afarangoz.bookstore.api.common.model.entity.Book;

public interface IBookRepository extends JpaRepository<Book, Long>{

}
