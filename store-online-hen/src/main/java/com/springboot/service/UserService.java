package com.springboot.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springboot.dto.user.ResponseDto;
import com.springboot.dto.user.SignInDto;
import com.springboot.dto.user.SignInResponseDto;
import com.springboot.dto.user.SignUpDto;
import com.springboot.exceptions.AuthenticationFailException;
import com.springboot.exceptions.CustomException;
import com.springboot.model.AuthenticationToken;
import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import com.springboot.util.ApiResponse;
import com.springboot.util.Helper;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;

	String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return myHash;
	}

	@Transactional
	public ResponseDto signUp(SignUpDto signupDto) throws CustomException {
		if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			// we have user - w need get exception
			throw new CustomException("user allready exist");
		}
		// hash password
		String encryptedPassword = signupDto.getPassword();
		try {
			encryptedPassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("hashing password failed {}", e.getMessage());
		}

		User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
				encryptedPassword);

		// save user
		userRepository.save(user);

		// create the token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		// save token in database
		authenticationService.saveConfirmationToken(authenticationToken);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("user created successfully");
		responseDto.setStatus("success");
		return responseDto;
	}

	public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        
        if(Objects.isNull(user)){
            throw  new AuthenticationFailException("user not valid");
        }
        
        //hash the password
        
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passowrd doesnot match
                throw  new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if(Objects.isNull(token)) {
            // token not present
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto ("success", token.getToken());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
