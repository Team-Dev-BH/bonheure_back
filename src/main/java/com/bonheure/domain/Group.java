package com.bonheure.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;

import javax.persistence.*;

@Entity(name = "groupes")
@Table(name = "groupes",indexes = { @Index(name = "index_group_reference", columnList = "reference", unique = true) })
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String reference;

	@ManyToMany
	private Set<Prestation> prestations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Set<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(Set<Prestation> prestations) {
		this.prestations = prestations;
	}
}
