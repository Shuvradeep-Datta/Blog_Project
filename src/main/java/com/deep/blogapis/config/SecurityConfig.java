//package com.deep.blogapis.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.deep.blogapis.security.CustomUserDetailService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  {
//	@Autowired
//	CustomUserDetailService customUserDetailsService;
//	 	
//	
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//					.authorizeHttpRequests((authorizeHttpRequest)-> authorizeHttpRequest.
//							requestMatchers("/**").
//							hasRole("ADMIN"))
//					.formLogin();
//		
//							
//		return httpSecurity.build();
//	}
//
//		
//}
