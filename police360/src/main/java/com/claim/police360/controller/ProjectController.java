package com.claim.police360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claim.police360.repository.ProjectRepository;
import com.claim.police360.model.projects;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;

	@GetMapping("projects")
	public String projects(Model model) {
		model.addAttribute("msg", "Projects");
		return "projects";
	}
	
	@PostMapping("addProject")
	public String addProject(Model model, @RequestParam String title, @RequestParam String location, @RequestParam String description, @RequestParam String department) {
		System.out.println("Title=" + title + " Location=" + location + "desc=" + description);
		
		projects newProject = new projects();
		newProject.setPname(title);
		newProject.setLocation1(location);
		newProject.setDescription(description);
		newProject.setDepartment(department);
		
		projectRepository.save(newProject);
		
		return "redirect:profile";
	}
}
