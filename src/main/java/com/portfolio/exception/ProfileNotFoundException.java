package com.portfolio.exception;

@SuppressWarnings("serial")
public class ProfileNotFoundException extends RuntimeException {
	public ProfileNotFoundException(Long id) {
		super("No profile with id: " + id + " found.");
	}
}