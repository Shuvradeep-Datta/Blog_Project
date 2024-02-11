package com.deep.blogapis.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deep.blogapis.entities.Category;
import com.deep.blogapis.entities.Post;
import com.deep.blogapis.entities.User;
import com.deep.blogapis.exceptions.ResourceNotFoundException;
import com.deep.blogapis.payloads.PostDto;
import com.deep.blogapis.payloads.PostResponse;
import com.deep.blogapis.payloads.UserDto;
import com.deep.blogapis.repository.CategoryRepository;
import com.deep.blogapis.repository.PostRepositories;
import com.deep.blogapis.repository.UserRepository;
@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PostRepositories postRepositories;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
	User user = this.userRepository.findById(userId).
			orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
	
	
	Category category = this.categoryRepository.findById(categoryId).
			orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
	
	
	
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepositories.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepositories.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepositories.save(post);
		
		
		
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepositories.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		this.postRepositories.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir)    {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
			
		}
		else  {
			sort = Sort.by(sortBy).descending();
			
		}
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		
		Page<Post> pagePost = this.postRepositories.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		 Post post = this.postRepositories.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
		
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
		
		List<Post> posts = this.postRepositories.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user Id",userId));
		List<Post> posts = this.postRepositories.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
		
		
	}

	@Override
	public List<PostDto> searchPost(String keyWord) {
	
		List<Post> postSearch = this.postRepositories.findByTitleContaining(keyWord);
		List<PostDto> postDtos = postSearch.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
