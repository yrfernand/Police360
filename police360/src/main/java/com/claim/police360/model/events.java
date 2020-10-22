package com.claim.police360.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="events")
@Entity
public class events {
	
	
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		
		private String ename;
		private String location1;
		private String department;
		private String participants;
		private String dateCreated;
		
}
		