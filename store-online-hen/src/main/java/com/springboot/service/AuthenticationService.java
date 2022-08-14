package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.exceptions.AuthenticationFailException;
import com.springboot.model.AuthenticationToken;
import com.springboot.model.User;
import com.springboot.repository.TokenRepository;
import com.springboot.util.Helper;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;
    
    
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}
	
	  public AuthenticationToken getToken(User user) {
	        return tokenRepository.findTokenByUser(user);
	    }
	  
	   public User getUser(String token) {
	        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
	        if (Helper.notNull(authenticationToken)) {
	            if (Helper.notNull(authenticationToken.getUser())) {
	                return authenticationToken.getUser();
	            }
	        }
	        return null;
	    }
	  
	    public void authenticate(String token) throws AuthenticationFailException {
	        if (!Helper.notNull(token)) {
	            throw new AuthenticationFailException("AUTH_TOEKN_NOT_PRESENT");
	        }
	        if (!Helper.notNull(getUser(token))) {
	            throw new AuthenticationFailException("AUTH_TOEKN_NOT_VALID");
	        }
	    }
	
	
	
    
    
}
