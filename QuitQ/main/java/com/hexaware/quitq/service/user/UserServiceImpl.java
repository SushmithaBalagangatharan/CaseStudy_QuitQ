package com.hexaware.quitq.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.UserDTO;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.UserRepository;
import com.hexaware.quitq.service.JwtService;
import com.hexaware.quitq.service.cart.ICartService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;

	Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");
	
	
	@Override
	public UserInfo findUserById(Long userId) throws UserNotFoundException {
		logger.info("Fetching user by user ID {}", userId);
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
	}

	
	
	@Override
	public String deleteUserById(Long userId) throws UserNotFoundException {
		
		userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		
		userRepository.deleteById(userId);
		
		logger.warn("Deleted user by user ID {}", userId);
		
		return "User with ID:"+userId+" deleted successfully.";
	}
	


	@Override
	public String registerUser(UserDTO user) {
		
		if (user.getPassword() == null || user.getPassword().isBlank()) {
			logger.error("Password should not be null or blank");
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
		
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(user.getUserName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfo.setGender(user.getGender());
        userInfo.setContactNumber(user.getContactNumber());
        userInfo.setCreatedAt(user.getCreatedAt());
        userInfo.setRole(user.getRole());
        
        userRepository.save(userInfo);
        logger.info("Registered user {}", userInfo);
        
		return "Registered In successfully";
	}

	@Override
	public List<UserInfo> getAllUser() {
		logger.info("Fetching all users");
		return userRepository.findAll();
	}

	@Override
	public UserInfo findUserProfileByJwt(String jwt) throws UserNotFoundException {
		String userName = jwtService.extractUsername(jwt);
		UserInfo userInfo = userRepository.findByUserName(userName);
		if(userInfo == null) {
			logger.error("User doesn't exist");
			throw new UserNotFoundException();
		}
		
		logger.info("Found user profile using jwt, user : {}", userInfo);
		return userInfo;
	}

}
