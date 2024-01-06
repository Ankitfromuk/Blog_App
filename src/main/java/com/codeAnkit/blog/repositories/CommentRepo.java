package com.codeAnkit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeAnkit.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
