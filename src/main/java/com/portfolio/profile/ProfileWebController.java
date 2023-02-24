package com.portfolio.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ProfileWebController {
	
	ProfileRepository profileRepository;

	ProfileWebController(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	
	
	
}