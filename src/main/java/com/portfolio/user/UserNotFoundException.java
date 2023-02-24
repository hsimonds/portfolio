package com.portfolio.user;


@SuppressWarnings("serial")
class UserNotFoundException extends RuntimeException {
	UserNotFoundException(Long id) {
		super("User with id: " + id + " not found");
	}
}