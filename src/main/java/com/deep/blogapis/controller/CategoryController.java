package com.deep.blogapis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.blogapis.payloads.ApiResponse;
import com.deep.blogapis.payloads.CategoryDto;
import com.deep.blogapis.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//Post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory= this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	//Put
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto updatedCategory= this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId){
	 this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Successfully",true),HttpStatus.OK);
	}
	//Get By id
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> readCategoryById(@PathVariable Integer catId){
		CategoryDto categoryDto = this.categoryService.readCategoryById(catId);
		return new  ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	//Get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> readCategory(){
		 List<CategoryDto> readCategory = this.categoryService.readCategory();
		return ResponseEntity.ok(readCategory);
		
	}
	

}
