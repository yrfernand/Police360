package com.claim.police360.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.claim.police360.model.Addressess;
import com.claim.police360.model.projects;
import com.claim.police360.repository.AddressRepository;
import com.claim.police360.repository.ProjectRepository;
import com.claim.police360.repository.UserRepository;
import com.claim.police360.service.AccountService;
import com.claim.police360.utils.DataValidation;
import com.claim.police360.utils.WebUtils;
import com.claim.police360.model.users;




@Controller
@SessionAttributes({"loggedInuser","role"})
public class UserController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserRepository userRepository;
	//private UserRepository userRepository;
	
	@Autowired
	private WebUtils webUtils;
	
	@Autowired
	private DataValidation dataValidation;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	ProjectRepository projectRepository;

	@PostMapping("register")
	@Transactional
	public String register(@ModelAttribute("users")users user, Model model, BindingResult result, RedirectAttributes red) {	
		
//		dataValidation.validate(user, result);
//		if (result.hasErrors()) {
//		model.addAttribute("profile", "active");
//		System.out.println("SIGN UP  FAILED");
//		return "signup";
//		}
		System.out.println("SAVING..");
		    accountService.saveAccount(user);
		    //user.setRole(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("USER"))));			
			model.addAttribute("msg","Profile");
			model.addAttribute("user_account", user);
			model.addAttribute("loggedInuser", user.getEmail2());
			//List<String> roles=user.getRoles().stream().map(x-> x.getRole()).distinct().collect(Collectors.toList());
			//model.addAttribute("role", user.getRole().stream().map(x-> x.getRole()).distinct().collect(Collectors.toList()));
		System.out.println("SIGN UP SUCCESS");
	    return "profile";
	}
	
	@GetMapping("profile") 
	  String profile(@SessionAttribute(required = false) String loggedInuser, Model model) {
	     try {
	    	System.out.println("HEADING TO PROFILE");
	    	System.out.println("LIU=" + loggedInuser);
	    	 //if user is not in session return login page expired session
			if(loggedInuser ==null || loggedInuser.isEmpty()) {
			 model.addAttribute("error", "Expired session, please Login");
			 return "login"; 
			 }
			//populate user details from database 
			 accountService.findByEmail(loggedInuser).ifPresent(a->{
			 model.addAttribute("user_account", a);				 
//			 if(a.getAddress()!=null) {
//			    model.addAttribute("address", a.getAddress());	 
//			 }

			 });
			 List<projects> myList = projectRepository.findAll();
			 System.out.println("LIST SIZE" + myList.size());
			 model.addAttribute("page", "Profile");
			 model.addAttribute("allProjects", myList);
	     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  return "profile";
	 }
	
	@PostMapping("login")
	String login(RedirectAttributes redirect, Model model, @RequestParam String email, @RequestParam String password){
	  // login user
	  Optional<com.claim.police360.model.users> user= accountService.login(email, password);
	 //add user email and role in session
	  System.out.println("LOGGING IN");
	  if(user.isPresent()) {
		  System.out.println("USER FOUND");
		  model.addAttribute("loggedInuser", email);
		  //model.addAttribute("role", user.get().getRole().stream().map(x-> x.getRole()).distinct().collect(Collectors.toList())); 
		  
	  }else {
		  System.out.println("NO SUCH USER");
		  redirect.addFlashAttribute("error", "Sorry Invalid Credentials");
		  return "redirect:/login";
	  }
	  
	return "redirect:/profile";
		
	}

	@GetMapping("logout")
	String logout(Model model, SessionStatus status, HttpSession session){
		//status.setComplete();
		session.invalidate();
		model.addAttribute("loggedInuser", "");
		model.addAttribute("role", "");
		model.addAttribute("msg", "You have been Logged out");
	
	return "login";
		
	}

	@GetMapping("expired")
	String expire(Model model, HttpSession session){
		session.invalidate();
		model.addAttribute("loggedInuser", "");
		model.addAttribute("role", "");
		model.addAttribute("error", "You have been logged out due to inactivity");	
		
	return "login";
		
	}
	
	
	@GetMapping("deleteUser")
	public String delete(@RequestParam Long id, RedirectAttributes red) {
		
		try {
			accountService.deleteAccounts(id);
			red.addFlashAttribute("success", "Delete Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:admin";
	}
	
	@GetMapping("deleteByemail")
	public String dleteByemail(@RequestParam String email, RedirectAttributes red) {
				
		try {
			//accountService.delete(accountService.findByEmail(email).get());
			red.addFlashAttribute("success", "user deleted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			red.addFlashAttribute("error", " Sorry unexpected error delete fail");
		}
		
		return "redirect:admin";
	}
	
	
	@GetMapping("admin")
	public String users(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
				        @RequestParam(value = "size", defaultValue = "4", required = false) Integer size, @SessionAttribute(required = false) String role) {
    	    
    	    try {
    	    	if(role.equals("USER")) {    	    		
    	    		return "redirect:profile?protected=true";
    	    	}
    	    	if(role ==null || role.isEmpty()) {
    	    		 model.addAttribute("error", "Please Login");
    	    		return "login";
    	    	}
    	    	model.addAttribute("users", "active");
    	    	Page<users> findAllPagable = accountService.findAll(PageRequest.of(page, size, Sort.by("fname2")));				
				model.addAttribute("list", findAllPagable);
				model.addAttribute("msg"," Accounts found");
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return "admin";
		
	}
	
	@PostMapping("updateUsers")
	public String update(@ModelAttribute users user, Model model, RedirectAttributes red) {	
		
		try {
			
			  accountService.findById(user.getId()).ifPresent(a->{
			  a.setFname2(user.getFname2()); a.setLname2(user.getLname2());
			  a.setRole(user.getRole()); 
			  accountService.saveAccount(a);
			  
			  });
			 
			 
			 //userRepository.save(user);
			 red.addFlashAttribute("success", "User Updated"); 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//red.addFlashAttribute("error", "User Exists");
			
		
	  return "redirect:admin";
	}
	
	@PostMapping("/addimages")
	public String add(@RequestParam("file") MultipartFile file, 
			@RequestParam Long id, RedirectAttributes model) {
    	    
		Pattern ext = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg|pdf|mp4))$)");
		try {
			
			  if(file != null && file.isEmpty()){
				  model.addFlashAttribute("error", "Error No file Selected "); 
			      return "redirect:profile"; 
			      } 
			  if(file.getSize()>1073741824){
				  model.addAttribute("error","File size "+file.getSize()+"KB excceds max allowed, try another photo ");
				  return "redirect:profile"; 
			      } 
			  Matcher mtch = ext.matcher(file.getOriginalFilename());
			  
			  if (!mtch.matches()) {
				  model.addFlashAttribute("error", "Invalid Image type "); 
			      return "redirect:profile";			  
			  }
			
			   //((Object) webUtils).addProfilePhoto(file, id, "users");
				
			model.addFlashAttribute("msg", "Upload success "+ file.getSize()+" KB");
			
		} catch (Exception e) {
			//e.printStackTrace);
		}

		return "redirect:profile";
	}
	
	@PostMapping("sendemail")
	String sendemail(@RequestParam String email, 
					 @RequestParam String name,
					 @RequestParam String subject,
					 @RequestParam String message, RedirectAttributes red) {
		
			try {
				webUtils.sendMail(email, message+" From "+ name, subject);
				red.addFlashAttribute("success", "Your message has been sent. Thank you! "+ name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				red.addFlashAttribute("error", "Email fail! ");
			}		
	 
	return "redirect:contact";		
	}
	
	@PostMapping("search")
	public String search(Model model, @RequestParam String keyword) {
		
		Page<users> accounts=accountService.search(keyword, PageRequest.of(0, 4, Sort.by("id")));
		model.addAttribute("list", accounts);
		model.addAttribute("msg"," Accounts found");
		
		return "admin";
	}
	
	@PostMapping("editrole")
	public String editrole(Model model, @RequestParam String role, @RequestParam Long id) {
		
		accountService.editRoles(role, id);
		
		return "redirect:admin";
	}
	
	@PostMapping("seachByemailOrlastname")
	public String seachByemailOrlastname(Model model, @RequestParam String lname, @RequestParam String email) {
		
		Page<users> accounts=accountService.customeseacher(lname, email, PageRequest.of(0, 4, Sort.by("id")));
		model.addAttribute("list", accounts);
		model.addAttribute("msg"," Accounts found");
		
		return "admin";
	}
	
	@PostMapping("updatecontact")
	String update(@ModelAttribute Addressess addressess, Model model) {
	
		try {
			addressess.setCreatedon(new Date());
			users user=accountService.findById(addressess.getId()).get();
			user.setFname2(addressess.getUser().getFname2());
			user.setLname2(addressess.getUser().getLname2());
			user.setAddressess(addressess);
			accountService.saveAccount(user);	
			model.addAttribute("msg", "Update success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:profile";	}
		
	
	
	
			
	
		
		
	
	

	
	
	@PostMapping("changepassword")	
	String register(@ModelAttribute users user, RedirectAttributes mod) {
		String passwordRegex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
		
		    accountService.findById(user.getId()).ifPresent(a->{			
			
			if(!a.getPassword2().equals(user.getPassword2())) {
				mod.addFlashAttribute("error", "Password is different from current one");
			}
			
			if(!user.getPassword2().matches(passwordRegex)) { 				
				mod.addFlashAttribute("error", "Password should be at least 8 characters, lower case, upper case and a special character."); 
			}
			
			if(a.getPassword2().equals(user.getPassword2()) && user.getPassword2().matches(passwordRegex)) {
				a.setPassword2(user.getPassword2());
				accountService.saveAccount(a);
				mod.addFlashAttribute("msg", "Password reset success");
			}
			
		});
		
		return "redirect:profile";
		
	}
	
	
	@ModelAttribute("address")
	Addressess address() {
	return new Addressess();		
	}
	
	@ModelAttribute("user")
	users user() {		
		return new users();
		
	}
	
	
}
