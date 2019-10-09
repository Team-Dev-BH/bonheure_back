package com.bonheure.controller.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.bonheure.domain.Role;

public class ClientDTO {

	private String reference;

	private String firstName;

	private String lastName;

	private String email;

	private String mobileNumber;

	private String password;

	private LocalDateTime creationDate;

	private LocalDateTime modificationDate;

	private LocalDateTime activationDate;

	private Boolean activated;

	private String position;

	private Role role;

	private String companyReference;

	private Set<String> groupReferences;

	private String adressReference;

	public String getAdressReference() {
		return adressReference;
	}

	public void setAdressReference(String adressReference) {
		this.adressReference = adressReference;
	}

	public String getCompanyReference() {
		return companyReference;
	}

	public void setCompanyReference(String companyReference) {
		this.companyReference = companyReference;
	}

	public String getReference() {
		return reference;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<String> getGroupReferences() {
		return groupReferences;
	}

	public void setGroupReferences(Set<String> groupReferences) {
		this.groupReferences = groupReferences;
	}

}