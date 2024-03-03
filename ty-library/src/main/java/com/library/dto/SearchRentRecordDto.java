package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchRentRecordDto {
	private String title;
	private String renter;
	private String rentDate;
	private String returnDate;
}
