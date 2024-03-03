package com.library.dto;

public class BookInfoDTO {
	
	private String title;
	private String category;
	private String author;
	private String publisher;
	private String isbn;
	
	//constructors
	public BookInfoDTO() {}
	public BookInfoDTO(String isbn, String title, String author, String publisher, String category) {
		super();
		this.title = title;
		this.category = category;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}

	public BookInfoDTO(String title, String author, String publisher, String category) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
	}
	
	
	//getter&setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
