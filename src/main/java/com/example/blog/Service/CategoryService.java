package com.example.blog.Service;

import java.util.List;

import com.example.blog.utils.CategoeyDto;

public interface CategoryService  {
	CategoeyDto createCategory(CategoeyDto dto);
	CategoeyDto updateCategoeyDto(CategoeyDto udto,Integer cid);
	void deleteCategory(Integer cid);
	CategoeyDto getCategoeyDto(Integer cid);
	List<CategoeyDto> getAllCategory();
	

}
