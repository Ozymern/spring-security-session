package com.ozymern.spring.security.session.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozymern.spring.security.session.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// personalizado para buscar usuario por su email
	public User findByEmail(String email);

}
