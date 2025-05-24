package com.hexaware.quitq.service.user;

import com.hexaware.quitq.entity.User;

public interface IUserService {
	
	public User findUserById(Long userId);
	
	public void deleteUserById(Long userId);
	
}
