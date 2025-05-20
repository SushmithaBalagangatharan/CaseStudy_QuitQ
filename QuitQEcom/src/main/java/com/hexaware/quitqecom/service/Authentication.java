package com.hexaware.quitqecom.service;

import com.hexaware.quitqecom.component.User;

public interface Authentication {
	
	User registerUser(User user);
	//Seller
	User registerSeller(User seller);
	User loginUser(String email, String password);
	User loginSeller(String email, String password);
	boolean emailExists(String email);
}
