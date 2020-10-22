package com.claim.police360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
	
	@GetMapping("events")
	public String events(Model model) {
		model.addAttribute("msg", "Events");
		return "events";
	}


}


