package com.portfolio.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.portfolio.exception.DuplicateProfileException;
import com.portfolio.exception.NotAuthorizedException;
import com.portfolio.model.Profile;
import com.portfolio.model.User;
import com.portfolio.repository.ProfileRepository;

@Service
public class ProfileService {
	private final ProfileRepository profileRepository;
	private UserService userService;
	
	public ProfileService (ProfileRepository profileRepository, UserService userService) {
		this.profileRepository = profileRepository;
		this.userService = userService;
	}

	public Optional<Profile> findProfileByDisplayName(String displayName) {
		Optional<Profile> profile = profileRepository.findByDisplayName(displayName);
		return profile;
	}
	
	public Optional<Profile> updateProfile(Profile profile, Principal principal, BindingResult result) {
		// Get signed in users User object
		User currentUser = userService.findUserByUsername(principal.getName()).get();
		Profile currentUserProfile = currentUser.getProfile();
		if (profile.getId() != currentUserProfile.getId()) {
			throw new NotAuthorizedException("profile", profile.getDisplayName());
		}
		
		boolean foundDup = false;
		
		//If the passed displayName doesn't match the current users existing profile displayName, make sure another user isn't already using the new display name
		if (!(profile.getDisplayName().equals(currentUserProfile.getDisplayName()))) {
			Optional<Profile> foundProfile = findProfileByDisplayName(profile.getDisplayName());
			if (foundProfile.isPresent()) {
				if (foundProfile.get().getUser() != currentUserProfile.getUser()) {
					result.rejectValue("displayName", null, "Profile already exists with that display name");
					foundDup = true;
				}
				if (foundDup) {
					throw new DuplicateProfileException("Profile with that displayName already exists", profile, result);
				}
			}
		}
	
		currentUserProfile.setDisplayName(profile.getDisplayName());
		currentUserProfile.setGithub(profile.getGithub());
		currentUserProfile.setLinkedin(profile.getLinkedin());
		currentUserProfile.setPersonalWebsite(profile.getPersonalWebsite());
		currentUserProfile.setOtherWebsite(profile.getOtherWebsite());
		currentUserProfile.setLanguages(profile.getLanguages());
		currentUserProfile.setFrameworks(profile.getFrameworks());
		return Optional.of(profileRepository.save(currentUserProfile));
	}
	
	
	public void deleteProfile(Profile profile, Principal principal) {
		Optional<Profile> foundProfile = findProfileByDisplayName(profile.getDisplayName());
		if (foundProfile.isPresent()) {
			Profile existingProfile = foundProfile.get();
			if (!(existingProfile.getUser().getUsername().equals(principal.getName()))) {
				throw new NotAuthorizedException("profile", profile.getDisplayName());
			}
		profileRepository.deleteById(existingProfile.getId());
		}
	}
}
