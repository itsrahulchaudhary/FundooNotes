package com.fundoo.app.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fundoo.app.LoginException;
import com.fundoo.app.response.Response;
import com.fundoo.app.response.ResponseToken;
import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;
import com.fundoo.app.user.model.User;
import com.fundoo.app.user.repository.IUserRepository;
import com.fundoo.app.utility.JWTToken;
import com.fundoo.app.utility.StatusHelper;

@Service("userService")
public class UserServicesImplementation implements IUserServices{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTToken jWTToken;

	@Override
	public Response register(UserDTO userDTO) {
		Response response = null;
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);
		user.setRegisteredDate(LocalDate.now());
		userRepository.save(user);
		response = StatusHelper.statusInfo("status.register.success",200);
		return response;
	}

	@Override
	public ResponseToken login(LoginDTO loginDTO) {
		ResponseToken response = null;
		Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
		if (user.isPresent()) {
			if (passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
				String generatedToken = jWTToken.generateToken(String.valueOf(user.get().getUserId()));
				response = StatusHelper.tokenStatusInfo("status.login.success", 200, generatedToken,
						user.get().getName());
				return response;
			} else {
				throw new LoginException("Invalid Password ", -3);
			}

		} else {
			throw new LoginException("Invalid EmailId ", -3);
		}

	}

}
