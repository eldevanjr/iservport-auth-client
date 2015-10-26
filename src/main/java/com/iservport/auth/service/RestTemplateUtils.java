package com.iservport.auth.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Realiza operações comuns.
 * 
 * @author Eldevan Nery Junior
 *
 */
public abstract class RestTemplateUtils {
	
//	@Value("${helianto.trusted.client:helianto-trusted-client-with-secret}")
	protected String heliantoTrustedClient = "helianto-trusted-client-with-secret";
	
//	@Value("${helianto.trusted.client.secret:helianto}")
	protected String heliantoTrustedClientSecret = "helianto";
	
	protected static final String GRANT_TYPE = "password";
	
	protected static final String RESPONSE_TYPE = "token";	
	
	protected static final String ENDPOINT = "http://54.207.10.184";
	
	protected static final String AUTH_ENDPOINT = ENDPOINT+":9090";
	
	protected static final String APP_ENDPOINT = ENDPOINT+":8080";
	
	protected static final String TOKEN_ENDPOINT = AUTH_ENDPOINT+"/oauth/token";	
	
	protected RestTemplate restTemplate = new RestTemplate() ;
	
	/**
	 * 
	 * HttpEntity para chamadas POST e tipo de conteúdo {@link MediaType#APPLICATION_FORM_URLENCODED}
	 * 
	 * @param map
	 */
	protected HttpEntity<?> getHttpEntityForm(MultiValueMap<String, String> map){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " +new String(Base64.encode((heliantoTrustedClient+":"+heliantoTrustedClientSecret).getBytes())));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return new HttpEntity<>(map, headers);
	}
	
	/**
	 * 
	 * HttpEntity com token de autorização genérico.
	 * 
	 * @param map
	 */
	protected HttpEntity<?> getHttpEntityToken(MediaType mediaType, MediaType... accept) {
		HttpHeaders headers = new HttpHeaders();
		if(!OAuthTokenInMemory.hasAuthToken()){
			throw new IllegalArgumentException("OAuth token must be supplied");
		}
		headers.add("Authorization", "Bearer "+OAuthTokenInMemory.getAuthToken().getAccessToken());
		headers.setContentType(mediaType);
		headers.setAccept(Arrays.asList(accept));
		return new HttpEntity<>(headers);
	}
	
	/**
	 * Realiza a chamada 'REST'
	 * 
	 * @param builder
	 * @param method
	 * @param httpEntity
	 * @param clazz
	 * 
	 */
	protected ResponseEntity<?> getResponseEntity(UriComponentsBuilder builder, HttpMethod method, HttpEntity<?> httpEntity,Class<?> clazz){
		return restTemplate.exchange(builder.build().encode().toUri(), method, httpEntity, clazz);
	}
	
}
