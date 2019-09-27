package com.bonheure.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(name = "index_prestation_reference", columnList = "reference", unique = true) })
public class Prestation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String reference;

	private String parentRefPrest;

	private String name;

	private String tagList;

	@ManyToMany(mappedBy = "prestations")
	Set<Prestataire> prestataires;

	@ManyToMany(mappedBy = "prestations")
	Set<Categorie> categories;

	@ManyToMany
	Set<Groupe> groupes;

	@ManyToOne
	private Prestation parent;
	@OneToMany(mappedBy = "parent")
	private Collection<Prestation> children;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentRefPrest() {
		return parentRefPrest;
	}

	public void setParentRefPrest(String parentRefPrest) {
		this.parentRefPrest = parentRefPrest;
	}

	public String getTagList() {
		return tagList;
	}

	public void setTagList(String tagList) {
		this.tagList = tagList;
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
