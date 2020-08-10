package com.afarangoz.bookstore.api.common.model.dto;

import com.afarangoz.bookstore.api.common.model.entity.User;

public class UserDTO {

	private Long id;
	private String username;
	private String fullName;
	private String email;

	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
