package com.bonheure.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	private long id;
	
	private String reference;

	private String name;

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

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof Role))
			return false;
		
		return this.reference.equalsIgnoreCase(((Role)obj).getReference());
	}	
	
	
	
}
