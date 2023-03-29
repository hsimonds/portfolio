package com.portfolio.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
	
	@GetMapping("/")
    public String home(Model model, Principal principal){
        return "index";
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";		
	}
}