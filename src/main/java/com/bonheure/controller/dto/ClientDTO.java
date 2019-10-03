package com.bonheure.controller.dto;

import java.time.LocalDateTime;



public class ClientDTO extends UserDTO {
	
	private String companyRef;

	private String position;
	 
	private LocalDateTime birthDate;
	
	public String getCompanyRef() {
		return companyRef;
	}
	public void setCompanyRef(String companyRef) {
		this.companyRef = companyRef;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
