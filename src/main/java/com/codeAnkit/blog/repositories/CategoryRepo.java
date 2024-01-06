package com.codeAnkit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeAnkit.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
