package com.bonheure.repository;

import com.bonheure.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByReference(String reference);
}
