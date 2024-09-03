package com.example.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Model.Post;
import com.example.blog.Service.Postservice;
import com.example.blog.utils.ApiResponse;
import com.example.blog.utils.PostDto;
import com.example.blog.utils.PostResponse;

@RestController
@RequestMapping("/api/")
public class PostControler {

	@Autowired
	private Postservice postservice;

	@PostMapping("{userId}/{categoryId}/pos")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto po, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto post = postservice.createPost(po, userId, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> dto =  postservice.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(dto,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostYCategory(@PathVariable Integer categoryId){
		List<PostDto> dto =  postservice.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(dto,HttpStatus.OK);
	}
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId ){
		PostDto dto = this.postservice.getPostById(postId);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	@GetMapping("/abc")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir){
		PostResponse pr= this.postservice.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(pr,HttpStatus.OK);
	}
	@DeleteMapping("{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postservice.deletePost(postId);
		return new ResponseEntity<ApiResponse>( new ApiResponse("post is deleted", false),HttpStatus.OK);
	}
	@PutMapping("{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post,@PathVariable Integer postId ){
		PostDto dto = this.postservice.updatePost(post, postId);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable("keywords") String keywords)
	{
		List<PostDto> postDtos=this.postservice.serachPost(keywords);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
}
