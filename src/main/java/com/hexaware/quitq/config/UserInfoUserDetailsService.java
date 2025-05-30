package com.hexaware.quitq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.repository.UserRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByUserName(username);
		
		if (user == null) {

			throw new UsernameNotFoundException(username);

		}

		return  new UserInfoUserDetails(user);
	}

}
