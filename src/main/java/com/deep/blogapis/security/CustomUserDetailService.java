//package com.deep.blogapis.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.deep.blogapis.entities.User;
//import com.deep.blogapis.exceptions.ResourceNotFoundException;
//import com.deep.blogapis.repository.UserRepository;
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("username", "email"+username, 0));
//		return user;
//		
//		
//	
//		
//	}
//
//}
