package com.deep.blogapis.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.deep.blogapis.entities.Category;
import com.deep.blogapis.entities.Comment;
import com.deep.blogapis.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String title;
	
	private String content;
	
	private  Date addedDate;
	
	private String imageName;
	
	
	private CategoryDto category;
		
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	

}
