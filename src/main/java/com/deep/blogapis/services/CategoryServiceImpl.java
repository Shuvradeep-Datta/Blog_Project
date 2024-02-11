package com.deep.blogapis.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.blogapis.entities.Category;
import com.deep.blogapis.exceptions.ResourceNotFoundException;
import com.deep.blogapis.payloads.CategoryDto;
import com.deep.blogapis.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category add = this.categoryRepository.save(cat);
		
		return this.modelMapper.map(add, CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat =this.categoryRepository.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
		
		cat.setCategoryName(categoryDto.getCategoryName());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updated = this.categoryRepository.save(cat);
		return this.modelMapper.map(updated, CategoryDto.class);
		
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
		this.categoryRepository.delete(cat);
	}

	@Override
	public List<CategoryDto> readCategory() {
		
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
		
		
	}

	@Override
	public CategoryDto readCategoryById(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	
}
