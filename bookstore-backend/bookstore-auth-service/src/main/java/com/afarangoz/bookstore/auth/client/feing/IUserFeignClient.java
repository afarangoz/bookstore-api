package com.afarangoz.bookstore.auth.client.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.afarangoz.bookstore.api.common.model.entity.User;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.USER_REST_RESOURCE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_BY_PATH;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SEARCH;

/**
 * The Interface IUserFeignClient.
 */
@FeignClient(name = "bookstore-user-service")
public interface IUserFeignClient {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@GetMapping(SLASH_SYMBOL + USER_REST_RESOURCE + SLASH_SYMBOL + SEARCH + SLASH_SYMBOL + FIND_BY_PATH)
	public User findByUsername(@RequestParam String username);
}