package com.deep.blogapis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info= @Info(
				title="Blog Open api",
				version="1.0.0",
				description = "Blog open Api documentation"),
		servers = @Server(
				url="http://localhost:8080",
				description = "Blog open Api documentation"
				))
public class BlogApisApplication  {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}



}
