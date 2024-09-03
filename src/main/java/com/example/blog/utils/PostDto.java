package com.example.blog.utils;

import java.util.Date;

import com.example.blog.Model.Category;
import com.example.blog.Model.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private UserDto user;
	private CategoeyDto category;

	
}


