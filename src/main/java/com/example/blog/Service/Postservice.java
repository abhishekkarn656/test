package com.example.blog.Service;

import java.util.List;

import com.example.blog.Model.Post;
import com.example.blog.utils.PostDto;
import com.example.blog.utils.PostResponse;

public interface Postservice {
	PostDto createPost(PostDto postDto,Integer cid,Integer uid);
	PostDto updatePost(PostDto dto,Integer postId);
	public void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNo,Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostsByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> serachPost(String keyword);
	
	
}
