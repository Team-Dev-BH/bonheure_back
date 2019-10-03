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
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the parentRefPrest
	 */
	public String getParentRefPrest() {
		return parentRefPrest;
	}
	/**
	 * @param parentRefPrest the parentRefPrest to set
	 */
	public void setParentRefPrest(String parentRefPrest) {
		this.parentRefPrest = parentRefPrest;
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
	 * @return the tagList
	 */
	public String getTagList() {
		return tagList;
	}
	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(String tagList) {
		this.tagList = tagList;
	}
	/**
	 * @return the prestataires
	 */
	public Set<Prestataire> getPrestataires() {
		return prestataires;
	}
	/**
	 * @param prestataires the prestataires to set
	 */
	public void setPrestataires(Set<Prestataire> prestataires) {
		this.prestataires = prestataires;
	}
	/**
	 * @return the categories
	 */
	public Set<Categorie> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}
	/**
	 * @return the groupes
	 */
	public Set<Groupe> getGroupes() {
		return groupes;
	}
	/**
	 * @param groupes the groupes to set
	 */
	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}
	/**
	 * @return the parent
	 */
	public Prestation getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Prestation parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public Collection<Prestation> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(Collection<Prestation> children) {
		this.children = children;
	}



}
