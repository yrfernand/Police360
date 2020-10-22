package com.claim.police360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

	@GetMapping("projects")
	public String projects(Model model) {
		model.addAttribute("msg", "Projects");
		return "projects";
	}
}
