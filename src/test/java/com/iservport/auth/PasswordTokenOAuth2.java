package com.iservport.auth;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.iservport.auth.service.OAuthTokenInMemory;
import com.iservport.auth.service.PasswordTokenOauthService;
import com.iservport.auth.service.TokenReturn;
import com.iservport.config.TestConfig;

/**
 * Classe para testar se o token de autorização foi tomado corretamente.
 * 
 * @author Eldevan Nery Junior
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={TestConfig.class})
//@Transactional
public class PasswordTokenOAuth2 {

//	@Inject
	private PasswordTokenOauthService passwordTokenOauthService = new PasswordTokenOauthService();
	
	@Test
	public void getToken(){
		String password = (String)JOptionPane
				.showInputDialog(null," Type password to admin@rokoit.com.br","Password",JOptionPane.PLAIN_MESSAGE);
		TokenReturn tokenReturn = passwordTokenOauthService.getToken("admin@rokoit.com.br", password , false);
		assertTrue(tokenReturn.getAccessToken()!=null && !tokenReturn.getAccessToken().isEmpty() );
		assertEquals(OAuthTokenInMemory.getAuthToken(), tokenReturn);
		System.err.println(passwordTokenOauthService.getResource(HttpMethod.GET, "/rest/api/workout/qualifier", null).getBody());
	}
		
}
