package com.library.dto;

import java.util.Date;

public class BooksDTO {

	private int b_id;
	private String isbn;
	private String b_status;
	private String stock_count_status;
	private Date due_date;
	
	// constructor
	public BooksDTO() {}
	public BooksDTO(int b_id, String isbn) {
		super();
		this.b_id = b_id;
		this.isbn = isbn;
	}
	public BooksDTO(int b_id, String b_status, Date due_date) {
		super();
		this.b_id = b_id;
		this.due_date = due_date;
		this.b_status = b_status;
	}
	public BooksDTO(int b_id, String isbn, String b_status, Date due_date) {
		super();
		this.b_id = b_id;
		this.isbn = isbn;
		this.b_status = b_status;
		this.due_date = due_date;
	}
	public BooksDTO(int b_id, String isbn, String b_status, String stock_count_status, Date due_date) {
		super();
		this.b_id = b_id;
		this.isbn = isbn;
		this.b_status = b_status;
		this.stock_count_status = stock_count_status;
		this.due_date = due_date;
	}
	
	// getter&setter
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getB_status() {
		return b_status;
	}
	public void setB_status(String b_status) {
		this.b_status = b_status;
	}
	public String getStock_count_status() {
		return stock_count_status;
	}
	public void setStock_count_status(String stock_count_status) {
		this.stock_count_status = stock_count_status;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
}
