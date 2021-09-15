package com.ae.test;

public class Details {

	private String company;
	private String email;
	
	public Details(String company, String email) {
		this.company=company;
		this.email=email;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
