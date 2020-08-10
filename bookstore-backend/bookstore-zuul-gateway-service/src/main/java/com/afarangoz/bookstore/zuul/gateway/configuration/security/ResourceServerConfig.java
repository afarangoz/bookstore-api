package com.afarangoz.bookstore.zuul.gateway.configuration.security;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.ALL_PATH_MATCHER;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.AUTH_SERVICE_ZUUL_URI;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.BOOK_SERVICE_ZUUL_URI;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ORDER_SERVICE_ZUUL_URI;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.USER_SERVICE_ZUUL_URI;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ADMIN;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.USER;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * The Class ResourceServerConfig.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Configure.
	 *
	 * @param resources the resources
	 * @throws Exception the exception
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(AUTH_SERVICE_ZUUL_URI + ALL_PATH_MATCHER).permitAll()
		.antMatchers(HttpMethod.POST, USER_SERVICE_ZUUL_URI).permitAll()
		.antMatchers(HttpMethod.GET, BOOK_SERVICE_ZUUL_URI + ALL_PATH_MATCHER, USER_SERVICE_ZUUL_URI + ALL_PATH_MATCHER).permitAll()
		.antMatchers(HttpMethod.POST, ORDER_SERVICE_ZUUL_URI + ALL_PATH_MATCHER).hasRole(USER)
		.antMatchers(BOOK_SERVICE_ZUUL_URI + ALL_PATH_MATCHER, ORDER_SERVICE_ZUUL_URI + ALL_PATH_MATCHER, USER_SERVICE_ZUUL_URI + ALL_PATH_MATCHER)
		 .hasRole(ADMIN)
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.authorizeRequests().antMatchers(ALL_PATH_MATCHER).permitAll();
	}

	/**
	 * Token store.
	 *
	 * @return the jwt token store
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Access token converter.
	 *
	 * @return the jwt access token converter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("*s58#dwrf");
		return jwtAccessTokenConverter;
	}

	/**
	 * Cors filter.
	 *
	 * @return the filter registration bean
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Access-Control-Allow-Origin"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(ALL_PATH_MATCHER, corsConfig);

		return source;
	}

}

