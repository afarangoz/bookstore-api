package com.afarangoz.bookstore.api.common.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.afarangoz.bookstore.api.common.model.entity.Book;

public class BookDTO {

	private Long id;

	@NotEmpty
	private String name;

	private String picture;

	@NotNull
	private Long price;

	@NotNull
	private Long available;

	public BookDTO() {
		super();
	}

	public BookDTO(Book book) {
		super();
		this.id = book.getId();
		this.name = book.getName();
		this.picture = book.getPicture();
		this.price = book.getPrice();
		this.available = book.getAvailable();
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
