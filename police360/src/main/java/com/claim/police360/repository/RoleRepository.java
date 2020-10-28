package com.claim.police360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.police360.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
	
}