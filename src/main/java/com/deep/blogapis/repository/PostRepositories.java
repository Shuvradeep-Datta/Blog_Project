package com.deep.blogapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deep.blogapis.entities.Category;
import com.deep.blogapis.entities.Post;
import com.deep.blogapis.entities.User;
import com.deep.blogapis.payloads.PostDto;
@Repository
public interface PostRepositories extends JpaRepository<Post, Integer> {
	
	List<Post>findByUser(User user);
	
	List<Post> findByCategory(Category category);

	List<Post>findByTitleContaining(String title);
}
