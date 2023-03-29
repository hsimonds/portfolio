package com.portfolio.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.portfolio.model.Profile;
import com.portfolio.model.User;
import com.portfolio.service.UserService;
import jakarta.validation.Valid;

/*
	All user routes except /register and /user/create require authentication. All registered users capable of logging in have a user entity in the DB.
	It is safe to assume that principal exists, is not null, and that user objects exist that correspond to the principal.
*/

@Controller
class UserController {
	private final UserService userService;
	
	UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String showCreateUserForm(Model model) {
		model.addAttribute("user", new User());
		return "create-user";		
	}
	
	@PostMapping("/register")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "create-user";
		}
	    Profile newUserProfile = userService.createUser(user, result).getProfile();
	    model.addAttribute("profile", newUserProfile);
		return "redirect:/login";
	}
	
	@GetMapping("/user")
	public String showUpdateUserForm(Model model, Principal principal) {
		//Fetch user for current principal. User is logged in, so we can assume the user exists
		User existingUser = userService.findUserByUsername(principal.getName()).get();
		model.addAttribute("user", existingUser);
		return "edit-user";
	}
	
	@PostMapping("/user")
	public String updateUser(@ModelAttribute("user") User user, BindingResult result, Model model, Principal principal) {
		//Manually validating username field here. Since password can be empty on an update, we don't want to use @Valid to validate entire user object
		if (user.getUsername() == null || user.getUsername() == "") {
			result.rejectValue("username", "error.username", "Username cannot be empty");
			return "edit-user";
		}

		User updatedUser = userService.updateUser(user, principal, result);
		model.addAttribute("message", "User updated successfully");
		// If user changed their username, they need to login again
		if (!(updatedUser.getUsername().equals(principal.getName()))) {
			return "redirect:/logout";
		}
		return "edit-user";
	}
	
	@DeleteMapping("/user")
	public String deleteUser(@ModelAttribute("user") User user, Principal principal) {
		userService.deleteUser(user, principal);
		return "index";
	}
}