package com.example.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Exception.ResourceNotFoundException;
import com.example.blog.Model.Category;
import com.example.blog.Repository.CategoryRepo;
import com.example.blog.Service.CategoryService;
import com.example.blog.utils.CategoeyDto;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoeyDto createCategory(CategoeyDto dto) {
		// TODO Auto-generated method stub
		Category cate=this.modelMapper.map(dto, Category.class);
		this.categoryRepository.save(cate);
		return this.modelMapper.map(cate, CategoeyDto.class);
	}

	@Override
	public CategoeyDto updateCategoeyDto(CategoeyDto udto, Integer cid) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepository.findById(cid).orElseThrow(()->
			new ResourceNotFoundException("Category","categoruId", cid)
		);
		cat.setCategoryDescription(udto.getCategoryDescription());
		cat.setCategoryTitle(udto.getCategoryTitle());
		 Category cats=categoryRepository.save(cat);
		
		return this.modelMapper.map(cats, CategoeyDto.class);
	}

	@Override
	public void deleteCategory(Integer cid) {
		// TODO Auto-generated method stub
		Category cats= this.categoryRepository.findById(cid).orElseThrow(()->
		new ResourceNotFoundException("Category","categoruId", cid)
	);
		this.categoryRepository.delete(cats);
		
	}

	@Override
	public CategoeyDto getCategoeyDto(Integer cid) {
		// TODO Auto-generated method stub
		Category cats= this.categoryRepository.findById(cid).orElseThrow(()->
		new ResourceNotFoundException("Category","categoruId", cid)
	);
		
		return this.modelMapper.map(cats, CategoeyDto.class);
	}

	@Override
	public List<CategoeyDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> category= categoryRepository.findAll();
		List<CategoeyDto> cdto= category.stream().map((cat)->
		this.modelMapper.map(cat, CategoeyDto.class)).collect(Collectors.toList());
		
		return cdto;
	}

}
