package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchQuery {
	private String category;
	private String searchBy;
	private String searchKey;
}
