package com.claim.police360.model;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name="users")
@Entity
public class users {
	
	
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		
		private String fname2;
		private String lname2;
		private String email2;
		private String password2;
		@Transient
		private String location2;
		private String dept2;
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFname2() {
			return fname2;
		}

		public void setFname2(String fname2) {
			this.fname2 = fname2;
		}

		public String getLname2() {
			return lname2;
		}

		public void setLname2(String lname2) {
			this.lname2 = lname2;
		}

		public String getEmail2() {
			return email2;
		}

		public void setEmail2(String email2) {
			this.email2 = email2;
		}

		public String getPassword2() {
			return password2;
		}

		public void setPassword2(String password2) {
			this.password2 = password2;
		}

		public String getLocation2() {
			return location2;
		}

		public void setLocation2(String location2) {
			this.location2 = location2;
		}

		public String getDept2() {
			return dept2;
		}

		public void setDept2(String dept2) {
			this.dept2 = dept2;
		}

		public String getRegistered2() {
			return registered2;
		}

		public void setRegistered2(String registered2) {
			this.registered2 = registered2;
		}

		private String registered2;
		
		{

}

		

		
			
		}
