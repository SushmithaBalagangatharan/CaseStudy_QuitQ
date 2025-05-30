package com.hexaware.quitq.service.user;

import java.util.List;

import com.hexaware.quitq.dto.UserDTO;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IUserService {
	
	public String registerUser(UserDTO user);
	
	public UserInfo findUserById(Long userId) throws UserNotFoundException;
	
	public String deleteUserById(Long userId) throws UserNotFoundException;
	
	public List<UserInfo> getAllUser();

	UserInfo findUserProfileByJwt(String jwt) throws UserNotFoundException;
		
	
}
