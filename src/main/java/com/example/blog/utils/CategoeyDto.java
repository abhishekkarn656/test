package com.example.blog.utils;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CategoeyDto {
	
	
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message = "Min Size of category is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min=4,message = "Min Size of category is 4")
     private String categoryDescription;
}
