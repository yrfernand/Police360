package com.claim.police360;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.claim.police360.model.users;

public class Police360ApplicationOne {

	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		
		@Autowired 
		private UserRepository UserRepository; 
		
		public static void main(String[] args) {
			SpringApplication.run(Police360Application.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			
			 Optional<users> usr= UserRepository.findByEmail("admin@email.com");
			
			if(!usr.isPresent()) {
				users user=new users();
				user.setFname2("Admin");
				user.setLname2("Manager");
				user.setEmail2("admin@email.com");
				user.setPassword2("123");
				//user.setRole("ADMIN");
				user.setRoles(new HashSet<Role>(roleRepository.findAll()));
		        userRepository.save(user);
		       
			}

	}

}
