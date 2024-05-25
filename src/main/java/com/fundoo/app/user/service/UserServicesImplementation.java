package com.fundoo.app.user.service;

import java.io.UnsupportedEncodingException;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 
import com.fundoo.app.exception.LoginException;
import com.fundoo.app.exception.RegistrationException;
import com.fundoo.app.response.Response;
import com.fundoo.app.response.ResponseToken;
import com.fundoo.app.user.dto.LoginDTO;
import com.fundoo.app.user.dto.UserDTO;
import com.fundoo.app.user.model.Email;
import com.fundoo.app.user.model.User;
import com.fundoo.app.user.repository.IUserRepository;
import com.fundoo.app.utility.JWTToken;
import com.fundoo.app.utility.MailService;
import com.fundoo.app.utility.StatusHelper;

@Service("userService")
public class UserServicesImplementation implements IUserServices{
	
	@Autowired
	private MailService mailServise;
	
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
		Email email = new Email();
		Response response = null;
		Optional<User> avaiability = userRepository.findByEmail(userDTO.getEmail());
		System.out.println("em "+userDTO.getEmail());
		if(avaiability.isPresent()) {
			throw new RegistrationException("User exixts", -2);
		}
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);
		user.setRegisteredDate(LocalDate.now());
		User saveResponse = userRepository.save(user);
		System.out.println("CODE app : test1");
		System.out.println("CODE app : test2");
		System.out.println("CODE app : test3");
		System.out.println("Magic app : test1");
		System.out.println("Magic app : test2");
		System.out.println("Magic app : test3");
		if(saveResponse == null) {
			throw new RegistrationException("Data is not saved in database", -2);
		}
		email.setFrom("fundoonote101@gmail.com");
		email.setTo(userDTO.getEmail());
		email.setSubject("Email Verification ");
		try {
			email.setBody(mailServise.getLink("http://localhost:8080/user/emailvalidation/",  String.valueOf(saveResponse.getUserId())));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailServise.send(email);
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

	@Override
	public Response validateEmail(String token) {
		Response response = null;
		String id = jWTToken.verifyToken(token);
		Optional<User> user = userRepository.findById(Integer.parseInt(id));
		if (user.isPresent()) {
			response = StatusHelper.statusInfo("status.email.verified", 200);
			return response;
		} else {
			throw new LoginException("EmailId is not verified", -3);
		}
	}

}
