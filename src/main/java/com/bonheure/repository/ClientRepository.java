package com.bonheure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByReference(String refrence);

}
