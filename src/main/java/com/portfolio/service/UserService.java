package com.portfolio.service;

import java.security.Principal;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.portfolio.exception.NotAuthorizedException;
import com.portfolio.exception.UserCreateException;
import com.portfolio.exception.UserUpdateException;
import com.portfolio.model.User;
import com.portfolio.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User createUser(User user, BindingResult result) {
		Optional<User> foundUserEmail = findUserByEmail(user.getEmail());
		Optional<User> foundUserUsername = findUserByUsername(user.getUsername());
		boolean dupUser = false;
		if (foundUserEmail.isPresent()) {
			result.rejectValue("email", null, "Account already exists with that email");	
			dupUser = true;
		}
		if (foundUserUsername.isPresent()) {
			result.rejectValue("username", null, "Account already exists with that username");
			dupUser = true;
		}
		if (dupUser) {
			throw new UserCreateException("Duplicate User", user, result);
		}
		
		// Roles not implemented, set to default
		user.setRoles("USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User updateUser(User user, Principal principal, BindingResult result) {
		// Get signed in users User object
		User currentUser = findUserByUsername(principal.getName()).get();
		
		// Make sure user being edited is that same as the logged in user
		if (user.getId() != currentUser.getId()) {
			throw new NotAuthorizedException("user", currentUser.getUsername());
		}
		
		// Check if new username or email are already in use
		Optional<User> foundUserEmail = findUserByEmail(user.getEmail());
		Optional<User> foundUserUsername = findUserByUsername(user.getUsername());
		boolean dupUser = false;
		
		// Throw an exception if username or email is already and use AND belongs to another user
		if (foundUserEmail.isPresent()) {
			if (foundUserEmail.get().getId() != currentUser.getId()) {
				result.rejectValue("email", null, "Account already exists with that email");
				dupUser = true;			}
		}	
		if (foundUserUsername.isPresent()) {
			if (foundUserUsername.get().getId() != currentUser.getId()) {
				result.rejectValue("username", null, "Account already exists with that username");
				dupUser = true;
			}
		}
		if (dupUser) {
			throw new UserUpdateException("Duplicate User", user, result); 
		}
		
		// Update and save new user data
		currentUser.setUsername(user.getUsername());
		currentUser.setFname(user.getFname());
		currentUser.setLname(user.getLname());
		currentUser.setEmail(user.getEmail());
		//Password update is optional for user, only update if password field wasn't blank.
		if (!user.getPassword().isEmpty()) {
			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		return userRepository.save(currentUser);			
	}
	
	public void deleteUser(User user, Principal principal) {
		Optional<User> foundUser = findUserByEmail(user.getEmail());
		if (foundUser.isPresent()) {
			User existingUser = foundUser.get();
			if (!(existingUser.getUsername().equals(principal.getName()))) {
				throw new NotAuthorizedException("user", existingUser.getUsername());
			}
			userRepository.deleteById(existingUser.getId());			
		}
	}
}
