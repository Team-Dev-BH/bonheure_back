package com.bonheure.repository;

import com.bonheure.domain.User;

import java.time.LocalDateTime;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByReference(String reference);
    User findByFirstName(String firstName);
    User findByCreationDate(LocalDateTime creationDate);
   
}
