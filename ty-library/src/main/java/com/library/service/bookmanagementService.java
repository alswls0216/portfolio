package com.library.service;

import com.library.dto.BookInfoDTO;
import com.library.dto.BooksDTO;
import com.library.dto.EmployeeDTO;

public interface bookmanagementService {

	EmployeeDTO checkEmplInfo(String e_id, String e_password);
	EmployeeDTO checkEmplInfoByBid(int b_id, String e_id, String e_password, String management_type);
	String getRecentReturnDate(int b_id);
	void updateBooks(int b_id, String due_date);
	void insertCheckout(int b_id, String e_id);
	void updateCheckout(int b_id, String e_id);
	
	BookInfoDTO selectBookInfo(int b_id);
	BooksDTO selectBooks(int b_id);
	EmployeeDTO selectEmplInfoByBid(int b_id);
	String selectCidByBid(int b_id);
}
