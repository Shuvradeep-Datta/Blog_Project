package com.deep.blogapis.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deep.blogapis.config.AppConstants;
import com.deep.blogapis.payloads.ApiResponse;
import com.deep.blogapis.payloads.CategoryDto;
import com.deep.blogapis.payloads.ImageResponse;
import com.deep.blogapis.payloads.PostDto;
import com.deep.blogapis.payloads.PostResponse;
import com.deep.blogapis.services.FileService;
import com.deep.blogapis.services.PostService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api")
public class PostController {
	
	
	//Create
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value(value = "$(project.image)")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//Get All post By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		
	}
	
	
	
	//Get All post By Category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		
	}
	
	//Get All
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber ,
			@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value ="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir)
	{
		PostResponse posts = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse> (posts,HttpStatus.OK);
	}
	
	//Get all by id
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto posts = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto> (posts,HttpStatus.OK);
	}
	
	//Delete POST
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePostById(@PathVariable Integer postId)
	{
		
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Successfully Deleted!!",true);
	}
	
	//Update POST
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto posts = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(posts,HttpStatus.OK);
		
	}
	
	
	//Search Post
	
	@GetMapping("/posts/search/{keyWords}")
	public ResponseEntity<List<PostDto>> searchKeywords(
			@PathVariable("keyWords") String keyWords){
		
		List<PostDto> searchPost = this.postService.searchPost(keyWords);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
		
	}
	
	
	//Post IMAGE Upload
	
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable("postId") Integer postId,
			@RequestParam("image") MultipartFile image) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	
	//mrthod to serve files
	
	@GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
	
	

}
