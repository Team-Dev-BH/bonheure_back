package com.bonheure.repository;

import com.bonheure.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByReference(String reference);

	long deleteByReference(String reference);

	boolean existsByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);

	User findByEmail(String email);

	List<User> findByFirstName(String firstName);

}
