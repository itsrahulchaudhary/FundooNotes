package com.fundoo.app.user.service;

import org.springframework.stereotype.Service;

import com.fundoo.app.user.dto.UserDTO;

@Service
public interface IUserServices {
	public String register(UserDTO userDTO);

}
