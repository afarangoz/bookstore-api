package com.afarangoz.bookstore.api.common.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.afarangoz.bookstore.api.common.model.dto.OrderBookDTO;

@Entity
@Table(name = "ORDERS_BOOKS")
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_id")
	private Book book;
	
	private Long quantity;

	public OrderBook() {
		super();
	}

	public OrderBook(OrderBookDTO orderBookDTO) {
		super();
		this.id = orderBookDTO.getId();
		this.book = new Book(orderBookDTO.getBookDTO());
		this.quantity = orderBookDTO.getQuantity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
