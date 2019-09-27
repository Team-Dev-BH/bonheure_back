package com.bonheure.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SuperAdmin extends User {

	private LocalDateTime birthDate;

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

}
