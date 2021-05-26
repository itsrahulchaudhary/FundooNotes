package com.fundoo.app.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;
import com.fundoo.app.user.model.User;
import com.fundoo.app.user.repository.IUserRepository;

@Service("userService")
public class UserServicesImplementation implements IUserServices{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User register(UserDTO userDTO) {
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);
		user.setRegisteredDate(LocalDate.now());
		
		return userRepository.save(user);
	}

	@Override
	public String login(LoginDTO loginDTO) {
		String login = "";
		Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
		if(user.isPresent()) {
			if(passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
				 login = "Login Successfull !!!";
			}
			
		}else {
			 login = "User is not present";
		}
		return login;
	}

}
