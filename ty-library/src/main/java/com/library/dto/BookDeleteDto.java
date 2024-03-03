package com.library.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDeleteDto {
	private String b_id;
	private String isbn;
	private String title;
	private String author;
}

