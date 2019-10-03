package com.bonheure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Role findByName(String Name);


}
