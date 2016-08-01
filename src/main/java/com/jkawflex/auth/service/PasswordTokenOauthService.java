package com.iservport.auth.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Serviço para retornar o token de autorização OAuth2.
 * 
 * @author Eldevan Nery Junior.
 *
 */
@Service
public class PasswordTokenOauthService extends RestTemplateUtils{

	/**
	 * Realiza a chamada e retorna o token.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TokenReturn getToken(String username, String password, Boolean tokenOverride){
		if (OAuthTokenInMemory.hasAuthToken() && !tokenOverride) {
			return OAuthTokenInMemory.getAuthToken();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TOKEN_ENDPOINT);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", GRANT_TYPE);
		map.add("response_type", RESPONSE_TYPE);
		map.add("username", username);
		map.add("password", password);
		ResponseEntity<TokenReturn> response = (ResponseEntity<TokenReturn>)
				getResponseEntity(builder, HttpMethod.POST, getHttpEntityForm(map), TokenReturn.class);
		if(tokenOverride || !OAuthTokenInMemory.hasAuthToken()){
			OAuthTokenInMemory.setAuthToken(response.getBody());
		}
		return response.getBody();
	}
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> getResource(HttpMethod method, String resource, String... params){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(APP_ENDPOINT+resource);
		ResponseEntity<String> response = (ResponseEntity<String>)
				getResponseEntity(builder, method, getHttpEntityToken(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON), String.class);
		return response;
	}
	
}
