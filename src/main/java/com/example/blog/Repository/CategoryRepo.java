package com.example.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
