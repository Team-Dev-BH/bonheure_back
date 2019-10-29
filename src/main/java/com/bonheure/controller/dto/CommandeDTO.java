package com.bonheure.controller.dto;

import java.time.LocalDateTime;

public class CommandeDTO {
	
	
	private String reference;


	private LocalDateTime creationDate;
    
    private String status;
    
    private String delay;
    
    private String commantary;
    
    private String nature;//FM ou C
    
    private String paymentMode;
    
    private String adressReference;
    
    private String categoryReference;
    
    private String clientReference;
    
    private String prestationReference;

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
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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
	 * @return the delay
	 */
	public String getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(String delay) {
		this.delay = delay;
	}

	/**
	 * @return the commantary
	 */
	public String getCommantary() {
		return commantary;
	}

	/**
	 * @param commantary the commantary to set
	 */
	public void setCommantary(String commantary) {
		this.commantary = commantary;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the adressReference
	 */
	public String getAdressReference() {
		return adressReference;
	}

	/**
	 * @param adressReference the adressReference to set
	 */
	public void setAdressReference(String adressReference) {
		this.adressReference = adressReference;
	}

	/**
	 * @return the categoryReference
	 */
	public String getCategoryReference() {
		return categoryReference;
	}

	/**
	 * @param categoryReference the categoryReference to set
	 */
	public void setCategoryReference(String categoryReference) {
		this.categoryReference = categoryReference;
	}

	/**
	 * @return the clientReference
	 */
	public String getClientReference() {
		return clientReference;
	}

	/**
	 * @param clientReference the clientReference to set
	 */
	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	/**
	 * @return the prestationReference
	 */
	public String getPrestationReference() {
		return prestationReference;
	}

	/**
	 * @param prestationReference the prestationReference to set
	 */
	public void setPrestationReference(String prestationReference) {
		this.prestationReference = prestationReference;
	}

}
