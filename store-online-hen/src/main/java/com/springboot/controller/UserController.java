package com.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.user.ResponseDto;
import com.springboot.dto.user.SignInDto;
import com.springboot.dto.user.SignInResponseDto;
import com.springboot.dto.user.SignUpDto;
import com.springboot.repository.UserRepository;
import com.springboot.service.AuthenticationService;
import com.springboot.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

 //   @Autowired
  //  AuthenticationService authenticationService;
    
    
    @Autowired
    UserService userService;
    
    @PostMapping("/signUp")
    public ResponseDto Signup(@Valid @RequestBody SignUpDto signupDto) {
        return userService.signUp(signupDto);
    }
    
    
    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
    
    
}
