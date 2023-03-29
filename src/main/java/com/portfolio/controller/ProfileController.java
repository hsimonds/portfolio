package com.portfolio.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.portfolio.model.Profile;
import com.portfolio.service.ProfileService;
import com.portfolio.service.UserService;

import jakarta.validation.Valid;

@Controller
public class ProfileController {
	private final ProfileService profileService;
	private final UserService userService;
	
	ProfileController(ProfileService profileService, UserService userService) {
		this.profileService = profileService;
		this.userService = userService;
	}
	
	// View a specific user profile
	@GetMapping("/profile/{displayName}")
	public String showProfileForm(@PathVariable String displayName, Model model, Principal principal) {
		Optional<Profile> profile = profileService.findProfileByDisplayName(displayName);
		
		if (profile.isPresent()) {
			model.addAttribute("profile", profile.get());
			return "view-profile";
		}
		else {
			model.addAttribute("message", "Profile with that display name not found");
			return "view-profile";
		}	
	}
	
	// Go to edit page for currently logged in user, if user isn't logged in send them to login page
	@GetMapping("/profile")
	public String showUserProfileForm(Model model, Principal principal) {
		if (principal != null) {
			Profile profile = userService.findUserByUsername(principal.getName()).get().getProfile();
			model.addAttribute("profile", profile);
			return "edit-profile";
		}
		else {
			return "redirect:/login";
		}
	}
	
	// Update user profile
	@PostMapping("/profile/{displayName}")
	public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, BindingResult result, Model model, Principal principal){
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "Error updating profile");
			return "edit-profile";
		}
		Optional<Profile> updatedProfile = profileService.updateProfile(profile, principal, result);
		if (updatedProfile.isPresent()) {
			model.addAttribute("message", "Profile updated successfully");
			return "redirect:/profile";
		}
		else {
			model.addAttribute("errorMessage", "Profile not found");
			return "index";
		}
	}
	
	@DeleteMapping("/profile")
	public String deleteProfile(@ModelAttribute("profile") Profile profile, Principal principal) {
		profileService.deleteProfile(profile, principal);
		return "index";
	}
	
}