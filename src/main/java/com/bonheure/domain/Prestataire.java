package com.bonheure.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Set;

@Entity
@DiscriminatorValue("PRESTATAIRE")
public class Prestataire extends User implements Serializable {

	private String type;

	private String code;
	 

	 private LocalDateTime startActivityDate;

	private int employeesCount;

	private String registration;

	@ManyToMany
	private Set<Prestation> prestations;

	@OneToMany
	private Set<WorkingArea> workingAreas;

	@OneToOne
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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

	public LocalDateTime getStartActivityDate() {
		return startActivityDate;
	}

	public void setStartActivityDate(LocalDateTime startActivityDate) {
		this.startActivityDate = startActivityDate;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public int getEmployeesCount() {
		return employeesCount;
	}

	public void setEmployeesCount(int employeesCount) {
		this.employeesCount = employeesCount;
	}

	public Set<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(Set<Prestation> prestations) {
		this.prestations = prestations;
	}

	public Set<WorkingArea> getWorkingAreas() {
		return workingAreas;
	}

	public void setWorkingAreas(Set<WorkingArea> workingAreas) {
		this.workingAreas = workingAreas;
	}

}
