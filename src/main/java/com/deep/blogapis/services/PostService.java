package com.deep.blogapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep.blogapis.entities.Post;
import com.deep.blogapis.payloads.PostDto;
import com.deep.blogapis.payloads.PostResponse;

@Service
public interface PostService {
	
	//Create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//Update 
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//Delete
	void deletePost(Integer postId);
	
	
	//Read
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	
	//Get Single Post
	PostDto getPostById(Integer postId);
	
	//get All post By Category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get All post By User
	
	List<PostDto> getPostByUser(Integer userId);
	
	
	//Search Post
	
	List<PostDto> searchPost(String keyWord);

}
