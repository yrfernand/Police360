package com.claim.police360.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.claim.police360.model.Addressess;
import com.claim.police360.model.users;

public interface AccountService {
	
	void saveAccount(users users);
	
	void deleteAccounts(Long id);
	
    Optional<users> findByEmail(String email2);	
	
	Optional<users> login(String email2, String password2);	
	
	List<users> findByName(String fname2);	
	
	Page<users> search(String name, Pageable pageable);	
	
	List<users> findByName(String lname2, String email2);		
	
	Page<users> customeseacher(String name2, String email2, Pageable pageable);
	
	Page<users> findAll(Pageable pageable);
	
	Optional<users> findById(Long id);
	
	void editRoles(String role, Long id);
	
	void updatecontact(Addressess addressess);

}
