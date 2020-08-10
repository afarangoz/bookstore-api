package com.afarangoz.bookstore.auth.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.afarangoz.bookstore.api.common.model.entity.User;
import com.afarangoz.bookstore.auth.client.feing.IUserFeignClient;

import feign.FeignException;

@Service
public class UserService implements UserDetailsService, IUserService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/** The user feign client. */
	@Autowired
	private IUserFeignClient userFeignClient;

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Override
	public User findByUsername(String username) {
		return userFeignClient.findByUsername(username);
	}

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			User user = userFeignClient.findByUsername(username);

			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					user.getIsEnable(), true, true, true, authorities);
		} catch (FeignException e) {
			String message = "User " + username + " not exist";
			logger.error("FeignException: ", e);
			throw new UsernameNotFoundException(message);
		}
	}

}
