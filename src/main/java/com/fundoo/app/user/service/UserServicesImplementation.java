package com.fundoo.app.user.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoo.app.user.dto.UserDTO;
import com.fundoo.app.user.model.User;
import com.fundoo.app.user.repository.IUserRepository;

@Service("userService")
public class UserServicesImplementation implements IUserServices{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public String register(UserDTO userDTO) {
		
		User user = modelMapper.map(userDTO, User.class);
		user.setRegisteredDate(LocalDate.now());
		userRepository.save(user);
		return "success";
	}

}
