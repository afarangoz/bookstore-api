package com.afarangoz.bookstore.api.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.afarangoz.bookstore.api.common.model.dto.BookDTO;

@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	private String picture;

	private Long price;

	private Long available;

	public Book() {
		super();
	}

	public Book(BookDTO bookDTO) {
		super();
		this.id = bookDTO.getId();
		this.name = bookDTO.getName();
		this.picture = bookDTO.getPicture();
		this.price = bookDTO.getPrice();
		this.available = bookDTO.getAvailable();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

}
