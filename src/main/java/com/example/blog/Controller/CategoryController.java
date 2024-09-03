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
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Service.CategoryService;
import com.example.blog.utils.ApiResponse;
import com.example.blog.utils.CategoeyDto;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/create")
	public ResponseEntity<CategoeyDto> createCategory(@RequestBody CategoeyDto cat){
		CategoeyDto dyo=this.categoryService.createCategory(cat);
		return new ResponseEntity<CategoeyDto>(dyo,HttpStatus.OK);
	}
	@PutMapping("/{catId}")
	public ResponseEntity<CategoeyDto> updateCategory(@RequestBody CategoeyDto dto,@PathVariable("catId")Integer cid ){
		CategoeyDto cdto=this.categoryService.updateCategoeyDto(dto, cid);
		return new ResponseEntity<CategoeyDto>(cdto,HttpStatus.OK);
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoeyDto> getCategory(@PathVariable("categoryId") Integer cis){
		CategoeyDto dto =this.categoryService.getCategoeyDto(cis);
		return new ResponseEntity<CategoeyDto>(dto,HttpStatus.OK);
	}
	@DeleteMapping("{cid}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cid ){
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>( new ApiResponse("Category is deleted", false),HttpStatus.OK);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoeyDto>> getCategories(){
		List<CategoeyDto> categ=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoeyDto>>(categ,HttpStatus.OK);
}}
