package com.example.blog.ServiceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.blog.Exception.ResourceNotFoundException;
import com.example.blog.Model.Category;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Repository.CategoryRepo;
import com.example.blog.Repository.PostRepo;
import com.example.blog.Repository.UserRepo;
import com.example.blog.Service.Postservice;
import com.example.blog.utils.PostDto;
import com.example.blog.utils.PostResponse;
@Service
public class PostServiceImpl implements Postservice {
@Autowired
private PostRepo postRepository;
@Autowired
private ModelMapper modelMapper;
@Autowired
private CategoryRepo categoryRepo;
@Autowired
private UserRepo userRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer cid,Integer uid) {
		// TODO Auto-generated method stub
		User user =this.userRepository.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User","UserId", uid));
		Category c= this.categoryRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", cid));
		
		Post post =this.modelMapper.map(postDto,Post.class);
		post.setImageName("defaullt.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(c);
		Post newPost = this.postRepository.save(post);
		
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		// TODO Auto-generated method stub
		Post p= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("User","UserId", postId));
		p.setTitle(dto.getTitle());
		p.setContent(dto.getContent());
		p.setImageName(dto.getImageName());
		Post upost= this.postRepository.save(p);
		return this.modelMapper.map(upost, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNo,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort= Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
	    Page<Post> pagePost = this.postRepository.findAll(pageable);

	    // Logging for debugging
	    System.out.println("Total Elements: " + pagePost.getTotalElements());
	    System.out.println("Total Pages: " + pagePost.getTotalPages());
	    System.out.println("Current Page: " + pageNo);
	    System.out.println("Page Size: " + pageSize);

	    List<Post> allPosts = pagePost.getContent();
	    List<PostDto> postDtos = allPosts.stream()
	        .map(post -> this.modelMapper.map(post, PostDto.class))
	        .collect(Collectors.toList());
	     PostResponse postResponse = new PostResponse();
	     postResponse.setContent(postDtos);
	     postResponse.setPageNumber(pagePost.getNumber());
	     postResponse.setPageSize(pagePost.getSize());
	     postResponse.setTotalElments(pagePost.getTotalElements());
	     postResponse.setTotalPages(pagePost.getTotalPages());
	     postResponse.setLastPage(pagePost.isLast());
	     
	     return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post p = this.postRepository.findById(postId).get();
		PostDto dto = this.modelMapper.map(p, PostDto.class);

		return dto;

	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Optional<Category> cat= this.categoryRepo.findById(categoryId);
		List<Post> posts= this.postRepository.findByCategory(cat);
	List<PostDto> dto=	posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		System.out.println("hey................");
		User user= this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts =this.postRepository.findByUser(user);
		List<PostDto> dto =posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dto;
	}

	@Override
	public List<PostDto> serachPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts= this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepository.findById(postId).get();
		this.postRepository.delete(post);
		
	}

}
