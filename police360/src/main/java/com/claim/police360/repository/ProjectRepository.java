package com.claim.police360.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claim.police360.model.projects;
import com.claim.police360.model.users;

@Repository
public interface ProjectRepository  extends JpaRepository<projects, Long> { 
	
String search="SELECT p FROM projects p WHERE p.pname =:pname";
	
	@Query(search)
	Page<projects> filter(@Param("pname") String type, Pageable pageable);
	




} 





