package com.afarangoz.bookstore.user.model.respository;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.USER_REST_RESOURCE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_BY_PATH;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.afarangoz.bookstore.api.common.model.entity.User;

/**
 * The Interface IUserRepository.
 */
@RepositoryRestResource(path = USER_REST_RESOURCE)
public interface IUserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@RestResource(path = FIND_BY_PATH)
	public User findByUsername(@Param("username") String username);
}
