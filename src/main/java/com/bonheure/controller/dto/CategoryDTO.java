package com.bonheure.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bonheure.domain.Prestation;

public class CategorieDTO {

	private String reference;
	
	@NotNull
    @Size(max=20)
	private String name;
	
	private Prestation prestations;

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

	public Prestation getPrestations() {
		return prestations;
	}

	public void setPrestations(Prestation prestations) {
		this.prestations = prestations;
	}
	
	
	
	
}
