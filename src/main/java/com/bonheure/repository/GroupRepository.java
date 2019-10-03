package com.bonheure.repository;


import com.bonheure.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByReference(String reference);

}
