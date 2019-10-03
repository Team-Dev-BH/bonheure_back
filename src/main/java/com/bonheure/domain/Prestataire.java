package com.bonheure.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PRESTATAIRE")
public class Prestataire extends User {

	private String type;

	private String code;

	private LocalDateTime startActivityDate;

	private String employeesCount;

	private String registration;

	@OneToOne
	private Address address;

	@OneToMany
	private Set<WorkingArea> workingAreas;

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

	public LocalDateTime getStartActivityDate() {
		return startActivityDate;
	}

	public void setStartActivityDate(LocalDateTime startActivityDate) {
		this.startActivityDate = startActivityDate;
	}

	public String getEmployeesCount() {
		return employeesCount;
	}

	public void setEmployeesCount(String employeesCount) {
		this.employeesCount = employeesCount;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
