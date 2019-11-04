package com.bonheure.controller.dto;

 

public class QuotationDTO {
	
    private String reference;
    
    private Double price;
    
    private String status;
    
    private String prestataireReference;

    private String commandeReference;

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

	/**
	 * @return the price
	 */
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
	 * @return the prestataireReference
	 */
	public String getPrestataireReference() {
		return prestataireReference;
	}

	/**
	 * @param prestataireReference the prestataireReference to set
	 */
	public void setPrestataireReference(String prestataireReference) {
		this.prestataireReference = prestataireReference;
	}

	/**
	 * @return the commandeReference
	 */
	public String getCommandeReference() {
		return commandeReference;
	}

	/**
	 * @param commandeReference the commandeReference to set
	 */
	public void setCommandeReference(String commandeReference) {
		this.commandeReference = commandeReference;
	}

	 


}