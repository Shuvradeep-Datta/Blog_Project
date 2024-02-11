package com.deep.blogapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deep.blogapis.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
