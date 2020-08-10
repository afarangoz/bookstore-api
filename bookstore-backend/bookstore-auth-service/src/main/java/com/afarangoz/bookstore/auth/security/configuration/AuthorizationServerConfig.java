package com.afarangoz.bookstore.auth.security.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.afarangoz.bookstore.auth.security.AdditionalTokenInfo;

/**
 * The Class AuthorizationServerConfig.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/** The password encoder. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The additional token info. */
	@Autowired
	AdditionalTokenInfo additionalTokenInfo;

	/**
	 * Configure.
	 *
	 * @param security the security
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		super.configure(security);
	}

	/**
	 * Configure.
	 *
	 * @param clients the clients
	 * @throws Exception the exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("bookstore-frontend").secret(passwordEncoder.encode("*#06#"))
				.scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(3600);
		super.configure(clients);
	}

	/**
	 * Configure.
	 *
	 * @param endpoints the endpoints
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(additionalTokenInfo, accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
		super.configure(endpoints);
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

}