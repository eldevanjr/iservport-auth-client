package com.iservport.auth.service;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Retorno dos dados do token de autorização OAuth2.
 * 
 * @author Eldevan Nery Junior
 *
 */
public class TokenReturn {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonProperty("expires_in")
	private Integer expiresIn;

	private String scope;

	public TokenReturn() {
		super();	
	}

	public TokenReturn(String accessToken, String tokenType,
			String refreshToken, Integer expiresIn, String scope) {
		this();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.scope = scope;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "TokenReturn [accessToken=" + accessToken + ", tokenType="
				+ tokenType + ", refreshToken=" + refreshToken + ", expiresIn="
				+ expiresIn + ", scope=" + scope + "]";
	}



}
