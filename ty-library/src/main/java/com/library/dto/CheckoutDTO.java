package com.library.dto;

import java.util.Date;

public class CheckoutDTO {
	
	private int c_id;
	private int b_id;
	private String e_id;
	private Date rent_date;
	private Date return_date;
	
	// constructor
	public CheckoutDTO() {}
	public CheckoutDTO(int b_id, Date rent_date) {
		super();
		this.b_id = b_id;
		this.rent_date = rent_date;
	}
	public CheckoutDTO(int b_id, String e_id, Date rent_date) {
		this.b_id = b_id;
		this.e_id = e_id;
		this.rent_date = rent_date;
	}
	public CheckoutDTO(int c_id, int b_id, String e_id, Date rent_date, Date return_date) {
		this.c_id = c_id;
		this.b_id = b_id;
		this.e_id = e_id;
		this.rent_date = rent_date;
		this.return_date = return_date;
	}
	
	// getter&setter
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public Date getRent_date() {
		return rent_date;
	}
	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
}
