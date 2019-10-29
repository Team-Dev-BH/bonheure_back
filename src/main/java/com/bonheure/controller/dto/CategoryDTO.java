package com.bonheure.controller.dto;

import java.util.Set;

import com.bonheure.domain.Prestation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CategoryDTO {

	private String reference;

	private String name;

	
	
	private Set<String> prestationsNames;

 

	public Set<String> getPrestationsNames() {
		return prestationsNames;
	}

	public void setPrestationsNames(Set<String> prestationsNames) {
		this.prestationsNames = prestationsNames;
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

}
