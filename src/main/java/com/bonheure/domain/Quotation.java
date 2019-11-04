package com.bonheure.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "index_quotation_reference", columnList = "reference", unique = true)})


public class Quotation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;
    
    private Double price;
    
    private String status;
    
    @OneToOne
    private Prestataire prestataire;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Commande commande;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @return the price
	 */


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

 

	/**
	 * @return the prestataire
	 */
	public Prestataire getPrestataire() {
		return prestataire;
	}

	/**
	 * @param prestataire the prestataire to set
	 */
	public void setPrestataire(Prestataire prestataire) {
		this.prestataire = prestataire;
	}

	/**
	 * @return the commande
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * @param commande the commande to set
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
    
	@Override
	public int hashCode() {
	    // TODO Auto-generated method stub
	    return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    // TODO Auto-generated method stub
	    return super.equals(obj);
	}

    
}