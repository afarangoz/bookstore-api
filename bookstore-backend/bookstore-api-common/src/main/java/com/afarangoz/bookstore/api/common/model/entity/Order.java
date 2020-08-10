package com.afarangoz.bookstore.api.common.model.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.afarangoz.bookstore.api.common.model.dto.OrderDTO;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderBook> orderBooks;

	public Order() {
		super();
	}

	public Order(OrderDTO orderDTO) {
		super();
		this.id = orderDTO.getId();
		this.date = orderDTO.getDate();
		this.user = new User(orderDTO.getUserDTO());
		this.orderBooks = orderDTO.getOrderBooks().stream().map(OrderBook::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}

}
