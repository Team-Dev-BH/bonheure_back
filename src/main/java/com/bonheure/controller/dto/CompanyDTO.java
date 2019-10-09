package com.bonheure.controller.dto;

import java.util.Set;

public class CompanyDTO {

	private String reference;

	private String code;

	private String name;

	private String activityField;

	private String description;

	private String principalAddressReference;

	private Set<String> addressesReferences;

	public Set<String> getAddressesReferences() {
		return addressesReferences;
	}

	public void setAddressesReferences(Set<String> addressesReferences) {
		this.addressesReferences = addressesReferences;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the activityField
	 */
	public String getActivityField() {
		return activityField;
	}

	/**
	 * @param activityField the activityField to set
	 */
	public void setActivityField(String activityField) {
		this.activityField = activityField;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrincipalAddressReference() {
		return principalAddressReference;
	}

	public void setPrincipalAddressReference(String principalAddressReference) {
		this.principalAddressReference = principalAddressReference;
	}

}