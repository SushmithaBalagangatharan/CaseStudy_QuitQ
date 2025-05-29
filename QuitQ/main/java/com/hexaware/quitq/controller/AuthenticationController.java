package com.hexaware.quitq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.repository.UserRepository;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.user.IUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	UserRepository userRepository;
//	@Autowired
//	/WebSecurityConfig securityConfig;
	@Autowired
	IUserService userService;
//	@Autowired
//	AuthenticationManager authenticationManager;
//	@Autowired
//	JwtService jwtService;
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ICartService cartService;
	
	@GetMapping("/info")
	public String demo() {
		return "Inside";
	}

	
/*	@PostMapping("/signup")
	public String userSignUp(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
        String lastName = user.getLastName();
   
        User isEmailExist = userRepository.findByEmail(email);
        
        if(isEmailExist != null) {
        	return "Email aldready exist";
        }
        
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(bCryptPasswordEncoder.encode(password));
        System.out.println(bCryptPasswordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser = userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);
        
        //Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
	
        Authentication authentication = authenticationManager.authenticate(
        	    new UsernamePasswordAuthenticationToken(email, password)
        	);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtService.generateToken((User) authentication.getPrincipal());
        
        return token;
	}
	
	@PostMapping("/signin")
	public String userLogin(@RequestBody UserDTO userLogin) {
		String email = userLogin.getEmail();
		String password = userLogin.getPassword();
		
		Authentication authentication = authenticationManager.authenticate(
        	    new UsernamePasswordAuthenticationToken(email, password)
        	);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
                
        String token = jwtService.generateToken((User) authentication.getPrincipal());;
        
        return token;

	}*/
	

}
