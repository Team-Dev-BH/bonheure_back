package com.bonheure.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.bonheure.controller.dto.PrestationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(indexes = { @Index(name = "index_category_reference", columnList = "reference", unique = true) })
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443853764079923105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String reference;

	@Column(unique = true)
	private String name;

	@ManyToMany(mappedBy = "categories")
	private Set<Prestation> prestations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public Set<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(Set<Prestation> prestations) {
		this.prestations = prestations;
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
