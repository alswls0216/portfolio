package com.library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchDto {
	
	private String b_id;
	private String isbn;
	private String b_status;
	private String stock_count_status;
	private String due_date;
    
}
