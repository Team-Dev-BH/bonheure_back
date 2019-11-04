package com.bonheure.controller.dto;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import com.bonheure.domain.Role;

public class PrestataireDTO {

	@Column(unique = true)
	private String reference;

	private String firstName;

	private String lastName;

	@Email
	private String email;

	private String mobileNumber;

	private String password;

	private LocalDateTime creationDate;

	private LocalDateTime modificationDate;

	private LocalDateTime activationDate;

	private Boolean activated;

	private Role role;

	private String type;

	private String code;

	private LocalDateTime startActivityDate;

	private int employeesCount;

	private String registration;

	private Set<String> prestationReferences;

	
	private Set<String> workingAreasReferences;

	private String addressReference;


	public String getAddressReference() {
		return addressReference;
	}



	public void setAddressReference(String addressReference) {
		this.addressReference = addressReference;
	}



	public String getReference() {
		return reference;
	}



	public Set<String> getWorkingAreasReferences() {
		return workingAreasReferences;
	}



	public void setWorkingAreasReferences(Set<String> workingAreasReferences) {
		this.workingAreasReferences = workingAreasReferences;
	}



	public Set<String> getPrestationReferences() {
		return prestationReferences;
	}

	public void setPrestationReferences(Set<String> prestationReferences) {
		this.prestationReferences = prestationReferences;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public LocalDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public int getEmployeesCount() {
		return employeesCount;
	}

	public void setEmployeesCount(int employeesCount) {
		this.employeesCount = employeesCount;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	
}
