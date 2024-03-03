package com.library.dto;

public class EmployeeDTO {
	
	private String e_id;
	private String e_name;
	private String e_password;

	//constructors
	public EmployeeDTO() {}
	public EmployeeDTO(String e_id) {
		super();
		this.e_id = e_id;
	}
	public EmployeeDTO(String e_id, String e_name) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
	}
	public EmployeeDTO(String e_id, String e_name, String e_password) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_password = e_password;
	}
	
	//getter&setter
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_password() {
		return e_password;
	}
	public void setE_password(String e_password) {
		this.e_password = e_password;
	}
	
}
