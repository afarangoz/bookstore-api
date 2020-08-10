package com.afarangoz.bookstore.api.common.model.dto;

import javax.validation.constraints.NotNull;

import com.afarangoz.bookstore.api.common.model.entity.OrderBook;

public class OrderBookDTO {

	private Long id;

	@NotNull
	private Long quantity;

	@NotNull
	private BookDTO bookDTO;

	public OrderBookDTO() {
		super();
	}

	public OrderBookDTO(OrderBook orderBook) {
		super();
		this.id = orderBook.getId();
		this.quantity = orderBook.getQuantity();
		this.bookDTO = new BookDTO(orderBook.getBook());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}
}
