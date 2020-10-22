package com.claim.police360.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="policeUsers")
@Entity
public class policeusers {
	
	
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		
		private String fname;
		private String lname;
		private String email;
		private String password;
		@Transient
		private String location;
		private String dept;
		private String badge;
		private String systemAdmin;
		private String registered;
		
		{
		
		
	}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDept() {
			return dept;
		}

		public void setDept(String dept) {
			this.dept = dept;
		}

		public String getBadge() {
			return badge;
		}

		public void setBadge(String badge) {
			this.badge = badge;
		}

		public String getSystemAdmin() {
			return systemAdmin;
		}

		public void setSystemAdmin(String systemAdmin) {
			this.systemAdmin = systemAdmin;
		}

		public String getRegistered() {
			return registered;
		}

		public void setRegistered(String registered) {
			this.registered = registered;
		}
		
		{
			
		}
	


