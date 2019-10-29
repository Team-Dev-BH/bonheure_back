package com.bonheure.controller.dto;

import com.bonheure.domain.Commande;
import com.bonheure.domain.Prestataire;

public class QuotationDTO {
	
    private String reference;
    
    private Double price;
    
    private String status;
    
    private Prestataire prestataireReference;

    private Commande commandeReference;

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
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the prestataireReference
	 */
	public Prestataire getPrestataireReference() {
		return prestataireReference;
	}

	/**
	 * @param prestataireReference the prestataireReference to set
	 */
	public void setPrestataireReference(Prestataire prestataireReference) {
		this.prestataireReference = prestataireReference;
	}

	/**
	 * @return the commandeReference
	 */
	public Commande getCommandeReference() {
		return commandeReference;
	}

	/**
	 * @param commandeReference the commandeReference to set
	 */
	public void setCommandeReference(Commande commandeReference) {
		this.commandeReference = commandeReference;
	}


}
