package com.bonheure.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(indexes = { @Index(name = "index_prestation_reference", columnList = "reference", unique = true) })
public class Prestation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396093767348706366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String reference;
	 @Column(unique = true)
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> tags;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestation_parent_id")
	private Prestation parent;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Category> categories;

	

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Prestation getParent() {
		return parent;
	}

	public void setParent(Prestation parent) {
		this.parent = parent;
	}

}
