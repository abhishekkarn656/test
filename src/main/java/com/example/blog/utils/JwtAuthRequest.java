package com.example.blog.utils;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	private String password;
}
