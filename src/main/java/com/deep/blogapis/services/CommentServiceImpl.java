package com.deep.blogapis.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.blogapis.entities.Comment;
import com.deep.blogapis.entities.Post;
import com.deep.blogapis.exceptions.ResourceNotFoundException;
import com.deep.blogapis.payloads.CommentDto;
import com.deep.blogapis.repository.CommentRepository;
import com.deep.blogapis.repository.PostRepositories;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRepositories postRepos;
	
	@Autowired
	CommentRepository commentRepos;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepos.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComments = this.commentRepos.save(comment);
		
		return this.modelMapper.map(savedComments, CommentDto.class);
		
		
	}

	@Override
	public void deleteComment(Integer id) {
		
		Comment comments = this.commentRepos.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", id));

		this.commentRepos.delete(comments);
	}

}
