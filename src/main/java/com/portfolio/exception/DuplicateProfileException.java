package com.portfolio.exception;

import org.springframework.validation.BindingResult;

import com.portfolio.model.Profile;

@SuppressWarnings("serial")
public class DuplicateProfileException extends RuntimeException {
	
	private Profile profile;
	private BindingResult bindingResult;
	
	public DuplicateProfileException(String message, Profile profile, BindingResult bindingResult) {
		super("message");
		this.profile = profile;
		this.bindingResult = bindingResult;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}	
}