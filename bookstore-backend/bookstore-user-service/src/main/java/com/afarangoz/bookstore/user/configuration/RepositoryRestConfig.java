package com.afarangoz.bookstore.user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.afarangoz.bookstore.api.common.model.entity.Role;
import com.afarangoz.bookstore.api.common.model.entity.User;

/**
 * The Class RepositoryRestConfig.
 */
@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer{

	/**
	 * Configure repository rest configuration.
	 *
	 * @param config the config
	 */
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);
		config.exposeIdsFor(User.class, Role.class);
	}

}