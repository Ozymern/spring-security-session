package com.ozymern.spring.security.session.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozymern.spring.security.session.models.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
