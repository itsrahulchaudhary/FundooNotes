package com.fundoo.app.user.service;

import org.springframework.stereotype.Service;

import com.fundoo.app.response.Response;
import com.fundoo.app.response.ResponseToken;
import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;


@Service
public interface IUserServices {
	public Response register(UserDTO userDTO);
	public ResponseToken login(LoginDTO loginDTO);

}
