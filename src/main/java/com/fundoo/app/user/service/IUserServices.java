package com.fundoo.app.user.service;

import org.springframework.stereotype.Service;

import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;
import com.fundoo.app.user.model.User;

@Service
public interface IUserServices {
	public User register(UserDTO userDTO);
	public String login(LoginDTO loginDTO);

}
