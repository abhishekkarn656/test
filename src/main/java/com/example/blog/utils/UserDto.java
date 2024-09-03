package com.example.blog.utils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min=4,message = "Username should be atleast 4 character")
	private String name;
	@Email(message = "Email not valid")
	private String email;
	@NotEmpty
	@Size(min = 4,message = "Password should be atleast four character")
	private String password;
	@NotEmpty
	private String about;

}
