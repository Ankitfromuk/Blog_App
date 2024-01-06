package com.codeAnkit.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeAnkit.blog.entities.Category;
import com.codeAnkit.blog.entities.Post;
import com.codeAnkit.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);	
	List<Post>findByTitleContaining(String title);

}
