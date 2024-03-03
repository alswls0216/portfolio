package com.library.dto;

public class BookDTO {

	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private String category;
	
	private String b_id;
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public BookDTO() {
		
	}
	
	public BookDTO(String isbn) {
		
		this.isbn=isbn;
	}
	
	public BookDTO(String isbn, String title, String author, String publisher, String category) {
		this.isbn=isbn;
		this.title=title;
		this.author=author;
		this.publisher=publisher;
		this.category=category;
	}
	
	public BookDTO(String b_id, String isbn, String title, String author) {
		this.isbn=isbn;
		this.b_id=b_id;
		this.title=title;
		this.author=author;
	}
	
	
	
		
}
