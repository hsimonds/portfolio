package com.portfolio.exception;


@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		super("User with id: " + id + " not found");
	}
	
	public UserNotFoundException(String username) {
		super("User with username: " + username + " not found");
	}
}