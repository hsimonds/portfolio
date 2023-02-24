package com.portfolio.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ProfileController {
	ProfileRepository profileRepository;

	ProfileController(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	@GetMapping("/profile/create")
	public String showCreateProfileForm(Model model) {
		Profile profile = new Profile();
		model.addAttribute("profile", profile);
		return "create-profile";
	}
	
	@GetMapping("/profile/{id}")
	public String showProfileEditForm(@PathVariable Long id, Model model) {
		Profile profile = profileRepository.findById(id)
				.orElseThrow(() -> new ProfileNotFoundException(id));
		model.addAttribute("profile", profile);
		return "edit-profile";
	}
	
	@PostMapping("/profile")
	public String createProfile(@ModelAttribute("profile") Profile profile) {
		profileRepository.save(profile);
		return "edit-profile";
	}
	
	@PostMapping("/profile/{id}")
	public String updateProfile(@ModelAttribute("profile") Profile profile, @PathVariable Long id) {
		profileRepository.save(profile);
		return String.format("redirect:/profile/%d", profile.getId());
	}
	
	@DeleteMapping("/profile/{id}")
	public String deleteProfile(@PathVariable Long id) {
		profileRepository.deleteById(id);
		return "redirect:/";
	}
	
}