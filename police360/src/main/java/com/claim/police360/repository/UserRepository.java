package com.claim.police360.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.claim.police360.model.users;


public interface UserRepository  extends JpaRepository<users, Long> { 
	
	String search="SELECT u FROM users u WHERE u.email2 =:email AND " +
	  		  "u.fname2 LIKE (CONCAT('%',:name, '%')) OR u.lname2 =:name";
	
	/*
	 * String searchusers="SELECT u FROM users u WHERE u.email =:name OR " +
	 * "u.fname LIKE (CONCAT('%',:name, '%')) OR u.lname LIKE (CONCAT('%',:name, '%'))"
	 * ;
	 */
	
	Optional<users> findByEmail2(String email);
	
	@Query("FROM users u WHERE u.email2=?1 AND u.password2=?2")
	Optional<users> login(String email, String password);
	
	@Query("FROM users WHERE lname2=?1 OR fname2=?1 OR email2=?1")
	List<users> findByName(String name);
	
	/*
	 * @Query(searchusers) Page<users> search(@Param("name") String name, Pageable
	 * pageable);
	 */
	
	@Query("FROM users WHERE lname2=?1 AND email2=?2")
	List<users> findByName(String lname, String email);
		
	@Query(search)
	Page<users> customeseacher(@Param("name") String name, @Param("email") String email, Pageable pageable);





} 

