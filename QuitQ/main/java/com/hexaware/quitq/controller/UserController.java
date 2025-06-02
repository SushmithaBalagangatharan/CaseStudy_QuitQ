package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.UserAuthDTO;
import com.hexaware.quitq.dto.UserDTO;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.JwtService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description User ReST Controller which contains end-points to handle HTTP request and provide response and provide authorization and authentication to user
 * @date 2-06-2025
 * @version 1.0
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;
    
    Logger logger = LoggerFactory.getLogger("UserController.class");
    
    @GetMapping("/message")
    public String getMessage() {
    	return "Hello all";
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO user) {
    	System.out.println("Received password: " + user.getPassword());
    	logger.info("Registering user");
    	return userService.registerUser(user);
    }
    
    
   @PostMapping("/login/auth")
	public String authenticateAndGetToken(@RequestBody UserAuthDTO authRequest) {
	   Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		

		String token = null;

		if (authentication.isAuthenticated()) {

			// call generate token method from jwtService class

			token = jwtService.generateToken(authRequest.getUserName());

			System.out.println("Token : " + token);

		} else {

			logger.error("Invalid user");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}

		logger.info("Logging user in");
		return token;
	}
   
   
   @GetMapping("/profile")
   @PreAuthorize("hasAnyAuthority('ADMIN , SELLER')")
   public UserInfo getUserProfile(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
	logger.debug("Fetching user profile");
	   return userService.findUserProfileByJwt(jwt);
   }
	
   @DeleteMapping("/deletebyid/{userId}")
   @PreAuthorize("hasAuthority('ADMIN')")
   public String deleteUserById(@PathVariable Long userId) throws UserNotFoundException {
	  logger.warn("Deleting user with user ID: {}", userId);
	   return userService.deleteUserById(userId);
   }
	
 
   @GetMapping("/getall")
   @PreAuthorize("hasAuthority('ADMIN')")
   public List<UserInfo> getAllUsers(){
	   logger.warn("Fetching all user");
	   return userService.getAllUser();
   }
   
  
   
}
