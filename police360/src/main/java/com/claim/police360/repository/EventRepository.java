package com.claim.police360.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.claim.police360.model.events;
import com.claim.police360.model.projects;
import com.claim.police360.model.users;

public interface EventRepository  extends JpaRepository<events, Long> { 
	
String search="SELECT e FROM events e WHERE e.ename =:ename";
	
	@Query(search)
	Page<projects> filter(@Param("ename") String type, Pageable pageable);
	

}
