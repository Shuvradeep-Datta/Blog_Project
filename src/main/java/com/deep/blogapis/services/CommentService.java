package com.deep.blogapis.services;

import org.springframework.stereotype.Service;

import com.deep.blogapis.payloads.CommentDto;

@Service
public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer id);

}
