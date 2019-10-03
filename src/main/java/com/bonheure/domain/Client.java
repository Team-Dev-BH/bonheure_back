package com.bonheure.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User {

	private String companyRef;

	private String position;
	private LocalDateTime birthDate;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Societe societe;

	@ManyToMany
	Set<Groupe> groupes;

	/**
	 * @return the companyRef
	 */
	public String getCompanyRef() {
		return companyRef;
	}

	/**
	 * @param companyRef the companyRef to set
	 */
	public void setCompanyRef(String companyRef) {
		this.companyRef = companyRef;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the societe
	 */
	public Societe getSociete() {
		return societe;
	}

	/**
	 * @param societe the societe to set
	 */
	public void setSociete(Societe societe) {
		this.societe = societe;
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



}
