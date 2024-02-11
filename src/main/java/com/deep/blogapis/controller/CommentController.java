package com.deep.blogapis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.blogapis.payloads.ApiResponse;
import com.deep.blogapis.payloads.CommentDto;
import com.deep.blogapis.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/posts/{id}/comments")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
		this.commentService.deleteComment(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!!",true),HttpStatus.OK);
		
	}
	
	
}
