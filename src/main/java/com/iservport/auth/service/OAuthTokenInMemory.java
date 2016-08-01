package com.iservport.auth.service;

/**
 * 
 * Save OAuth2 Token in memory.
 * 
 * @author Eldevan Nery Junior
 *
 */
public class OAuthTokenInMemory 
{
	
	private static ThreadLocal<TokenReturn> authToken = new ThreadLocal<>();

	public static TokenReturn getAuthToken() {
		return authToken.get();
	}
	
	public static Boolean hasAuthToken() {
		return authToken.get()!=null;
	}
	
	public static void setAuthToken(TokenReturn authToken) {
		OAuthTokenInMemory.authToken.set(authToken);
	}

}
