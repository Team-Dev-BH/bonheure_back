package com.bonheure.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Prestataire extends User {

	private String type;

	private String code;

	private LocalDateTime entActvDate;

	private String effective;

	private String registration;

	@ManyToMany
	Set<Prestation> prestations;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getEntActvDate() {
		return entActvDate;
	}

	public void setEntActvDate(LocalDateTime entActvDate) {
		this.entActvDate = entActvDate;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
