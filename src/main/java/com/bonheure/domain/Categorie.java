package com.bonheure.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(name = "index_categorie_reference", columnList = "reference", unique = true) })
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String reference;

	private String name;

	@ManyToMany
	Set<Prestation> prestations;

}
