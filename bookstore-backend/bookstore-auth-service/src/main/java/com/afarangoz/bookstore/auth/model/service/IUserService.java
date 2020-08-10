package com.afarangoz.bookstore.auth.model.service;

import com.afarangoz.bookstore.api.common.model.entity.User;

/**
 * The Interface IUserService.
 */
public interface IUserService {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User findByUsername(String username);
}