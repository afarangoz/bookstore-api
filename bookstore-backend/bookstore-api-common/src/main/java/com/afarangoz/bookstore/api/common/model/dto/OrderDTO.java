package com.afarangoz.bookstore.api.common.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.afarangoz.bookstore.api.common.model.entity.Order;

public class OrderDTO {

	private Long id;

	private Date date;

	@NotNull
	private UserDTO userDTO;

	@NotEmpty
	private List<OrderBookDTO> orderBooks;

	public OrderDTO() {
		super();
	}

	public OrderDTO(Order order) {
		super();
		this.id = order.getId();
		this.date = order.getDate();
		this.userDTO = new UserDTO(order.getUser());
		this.orderBooks = order.getOrderBooks() != null
				? order.getOrderBooks().stream().map(OrderBookDTO::new).collect(Collectors.toList())
				: null;
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<OrderBookDTO> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<OrderBookDTO> orderBooks) {
		this.orderBooks = orderBooks;
	}

}
