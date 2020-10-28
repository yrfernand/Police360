package com.claim.police360.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.claim.police360.model.Addressess;
import com.claim.police360.model.users;
import com.claim.police360.repository.RoleRepository;
import com.claim.police360.repository.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;

	@Override
	public Optional<users> findByEmail(String email) {		
		return userRepository.findByEmail2(email);
	}

	@Override
	public Optional<users> login(String email, String password) {		
		return userRepository.login(email, password);
	}

	@Override
	public List<users> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public Page<users> search(String name, Pageable pageable) {
		return ((AccountServiceImpl) userRepository).search(name, pageable);
	}

	@Override
	public List<users> findByName(String lname, String email) {
		return userRepository.findByName(lname, email);
	}

	@Override
	public Page<users> customeseacher(String name, String email, Pageable pageable) {
		return userRepository.customeseacher(name, email, pageable);
	}

	@Override
	public void saveAccount(users accounts) {
		userRepository.save(accounts);
	}

	@Override
	public void deleteAccounts(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Page<users> findAll(Pageable pageable) {		
		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<users> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void editRoles(String role, Long id) {		
		//userRepository.findById(id).get().setRole(role);
		userRepository.findById(id).
		ifPresent(a->{
//			if(role.equals("ADMIN")) {
//				a.setRoles(new HashSet<Role>(roleRepository.findAll()));
//			}
//			else {
//				a.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole(role))));
//			}				
		});

		
	}

	@Override
	public void updatecontact(Addressess addressess) {
		
		addressess.setCreatedon(new Date());
		users user=findById(addressess.getId()).get();
		user.setFname2(addressess.getUser().getFname2());
		user.setLname2(addressess.getUser().getLname2());
		user.setAddressess(addressess);
	}
}
	
