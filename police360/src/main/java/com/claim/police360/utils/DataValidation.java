package com.claim.police360.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.claim.police360.model.users;
import com.claim.police360.repository.UserRepository;




@Component
public class DataValidation implements Validator {
	
	@Autowired
	public UserRepository userRepository;
	
	String emailRegex ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	String passwordRegex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
	
	//String phoneRegex ="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
			
	public boolean supports(Class<?> clazz) {
		return users.class.equals(clazz);
	}

	public void validate(Object o, Errors errors) {
		users user = (users) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "size.user.fname");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "size.user.lname");			
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");		
		if (userRepository.findByEmail2(user.getEmail2()).isPresent()) {
	    	errors.rejectValue("email", "size.user.unique");
	    }
		       
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty");
        if (!user.getPassword2().equals(user.getPassword2())) {
	    	errors.rejectValue("password2", "match.user.password2");
	    }
        		
		if(!user.getEmail2().matches(emailRegex)) { 
			errors.rejectValue("email","size.user.email"); 
		}
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (!user.getPassword2().matches(passwordRegex)) {
        	errors.rejectValue("password", "size.user.password");
	    }
       
		
	}
}