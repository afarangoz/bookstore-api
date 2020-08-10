package com.afarangoz.bookstore.auth.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class SpringSecurityConfig.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/** The event publisher. */
	@Autowired
	private AuthenticationEventPublisher eventPublisher;

	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder()).and()
				.authenticationEventPublisher(eventPublisher);
	}

	/**
	 * Authentication manager.
	 *
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}