package com.deep.blogapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep.blogapis.payloads.CategoryDto;


@Service
public interface CategoryService {
	// Create
	public CategoryDto createCategory(CategoryDto categoryDto);

	// Update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// Delete
	public void deleteCategory(Integer categoryId);

	// Read
	public List<CategoryDto> readCategory();
	

	// Read by Id
	public CategoryDto readCategoryById(Integer categoryId);

}
