package com.bonheure.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(indexes = { @Index(name = "index_group_reference", columnList = "reference", unique = true) })
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String reference;
	

	@ManyToMany(mappedBy = "groupes")
	Set<Client> clients;
	
	@ManyToMany(mappedBy = "groupes")
	Set<Prestation> prestations;

	
	public Long getId() {
		return id;
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

}
