package com.claim.police360.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.police360.model.Addressess;

public interface AddressRepository extends JpaRepository<Addressess, Long> { 

	Optional<Addressess> findByEmail(String email);
	}

