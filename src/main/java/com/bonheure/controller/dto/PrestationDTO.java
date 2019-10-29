package com.bonheure.controller.dto;

import java.util.List;
import java.util.Set;

import com.bonheure.domain.Prestation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PrestationDTO {

	private String reference;

	private String name;

	private List<String> tags;

	private String parentReference;

	private Set<String> categoriesNames;

	 

	public Set<String> getCategoriesNames() {
		return categoriesNames;
	}

	public void setCategoriesNames(Set<String> categoriesNames) {
		this.categoriesNames = categoriesNames;
	}

	public String getParentReference() {
		return parentReference;
	}

	public void setParentReference(String parentReference) {
		this.parentReference = parentReference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
