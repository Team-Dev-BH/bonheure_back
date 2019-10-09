package com.bonheure.controller.dto;

import java.util.List;
import java.util.Set;

import com.bonheure.domain.Prestation;

public class PrestationDTO {

	private String reference;

	private String name;

	private List<String> tags;

	private Set<String> categoriesReferences;

	private String parentReference;
	

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

	public Set<String> getCategoriesReferences() {
		return categoriesReferences;
	}

	public void setCategoriesReferences(Set<String> categoriesReferences) {
		this.categoriesReferences = categoriesReferences;
	}

	// private String parent;

}
