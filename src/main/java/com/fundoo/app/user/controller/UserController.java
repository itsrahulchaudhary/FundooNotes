package com.fundoo.app.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundoo.app.response.Response;
import com.fundoo.app.response.ResponseToken;
import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;
 
import com.fundoo.app.user.service.IUserServices;


@RestController
@RequestMapping("/user")
public class UserController {
	
	//Creating Logger Object
	static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private IUserServices userServices;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserDTO userDTO) {
		System.out.println("master ....");
		System.out.println("testing ....");
		Response statusResponse = userServices.register(userDTO);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseToken> login(@RequestBody LoginDTO loginDTO) {
		System.out.println("master login");
		ResponseToken statusResponse = userServices.login(loginDTO);
		return new ResponseEntity<ResponseToken>(statusResponse, HttpStatus.OK);
	}

	@GetMapping("/emailvalidation/{token}")
	public ResponseEntity<Response > validateEmail(String token){
		Response statusResponse = userServices.validateEmail(token);
		return new ResponseEntity<Response> (statusResponse, HttpStatus.ACCEPTED);		
	}
}
