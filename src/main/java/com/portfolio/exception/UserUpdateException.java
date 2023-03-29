package com.portfolio.exception;

import org.springframework.validation.BindingResult;

import com.portfolio.model.User;

@SuppressWarnings("serial")
public class UserUpdateException extends RuntimeException {
	
	private User user;
	private BindingResult bindingResult;
	
	public UserUpdateException(String message, User user, BindingResult bindingResult) {
		super("message");
		this.user = user;
		this.bindingResult = bindingResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
	
}