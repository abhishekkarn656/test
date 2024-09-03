package com.example.blog.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blog.Model.Category;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Optional<Category> cat);
	
//	
//	@Query("select p from Post where p.title like:key")
//	List<Post> searchByTitle(@Param("key")String titles);
	
	
	List<Post> findByTitleContaining(String title);
}
