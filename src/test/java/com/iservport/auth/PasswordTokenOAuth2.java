package com.iservport.auth;

import static org.junit.Assert.assertTrue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.infokaw.udf.infokaw;
import com.iservport.auth.service.PasswordTokenOauthService;
import com.iservport.auth.service.TokenReturn;

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
	
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("localhost")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	

//	@Inject
	private PasswordTokenOauthService passwordTokenOauthService = new PasswordTokenOauthService();
	
	@Test
	public void getToken(){
		try {
			System.out.println(infokaw.Criptografar("h3l1@nt0"));
		} catch (InvalidKeyException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException
				| NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String password = (String)JOptionPane
				.showInputDialog(null," Type password to alaor@infokaw.com.br","Password",JOptionPane.PLAIN_MESSAGE);
		TokenReturn tokenReturn = passwordTokenOauthService.getToken("alaor@infokaw.com.br", password , false);
		assertTrue(tokenReturn.getAccessToken()!=null && !tokenReturn.getAccessToken().isEmpty() );
		System.out.println(tokenReturn);
//		assertEquals(OAuthTokenInMemory.getAuthToken(), tokenReturn);
		System.err.println(passwordTokenOauthService.getResource(HttpMethod.GET, "/adm/sync/time", null, tokenReturn.getAccessToken()).getBody());
	}
		
}
