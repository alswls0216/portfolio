package com.library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OverdueDto {
	private String title;
	private String e_name;
	private String due_date;
	private String day_difference;
}
