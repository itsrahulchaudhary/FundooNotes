package com.fundoo.app.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String register(@RequestBody UserDTO userDTO) {
		userServices.register(userDTO);
		return "success !!!";
	}
		

}
