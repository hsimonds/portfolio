package com.portfolio.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
class UserController {
	private final UserRepository userRepository;
	
	UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/user/create")
	public String showCreateUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "create-user";		
	}
	
	@GetMapping("/user/{id}")
	public String showEditUserForm(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		model.addAttribute("user", user);
		return "edit-user";
	}
	
	@PostMapping("/user")
	public String createUser(@ModelAttribute("user") User user, Model model) {
		userRepository.save(user);
		model.addAttribute(user);
		return String.format("redirect:/user/%d", user.getId());
	}
	
	@PostMapping("/user/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable Long id) {
		userRepository.save(user);
		return "edit-user";			
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "redirect:/";
	}
}