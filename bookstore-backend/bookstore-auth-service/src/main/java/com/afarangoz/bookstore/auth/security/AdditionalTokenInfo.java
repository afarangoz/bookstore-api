package com.afarangoz.bookstore.auth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.afarangoz.bookstore.api.common.model.entity.User;
import com.afarangoz.bookstore.auth.model.service.IUserService;

/**
 * The Class AdditionalTokenInfo.
 */
@Component
public class AdditionalTokenInfo implements TokenEnhancer {

	/** The user service. */
	@Autowired
	private IUserService userService;

	/**
	 * Enhance.
	 *
	 * @param accessToken    the access token
	 * @param authentication the authentication
	 * @return the o auth 2 access token
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<>();
		User user = userService.findByUsername(authentication.getName());
		additionalInfo.put("name", user.getFullName());
		additionalInfo.put("email", user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
