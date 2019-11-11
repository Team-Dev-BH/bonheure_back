package com.bonheure.domain;

import java.time.LocalDateTime;

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

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(indexes = {@Index(name = "index_commande_reference", columnList = "reference", unique = true)})
public class Commande {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;
    
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;
    
    private String status;
    
    private String delay;
    
    private String commantary;
    
    private String nature;//FM ou C
    
    private String paymentMode;
    
   @OneToOne
   private Prestation prestation ;
   
   @OneToOne
   private Category category;
    
   @OneToOne
   private Address address;
  
   @ManyToOne(cascade = CascadeType.REMOVE)
   private Client client;

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
 * @return the category
 */
public Category getCategory() {
	return category;
}

/**
 * @param category the category to set
 */
public void setCategory(Category category) {
	this.category = category;
}

/**
 * @return the address
 */
public Address getAddress() {
	return address;
}

/**
 * @param address the address to set
 */
public void setAddress(Address address) {
	this.address = address;
}
   


/**
 * @return the client
 */
public Client getClient() {
	return client;
}

/**
 * @param client the client to set
 */
public void setClient(Client client) {
	this.client = client;
}

/**
 * @return the prestation
 */
public Prestation getPrestation() {
	return prestation;
}

/**
 * @param prestation the prestation to set
 */
public void setPrestation(Prestation prestation) {
	this.prestation = prestation;
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