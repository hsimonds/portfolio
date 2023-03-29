package com.portfolio.exception;

@SuppressWarnings("serial")
public class NotAuthorizedException extends RuntimeException {
	public NotAuthorizedException(String resourceType, String resourceName) {
		super("Not authorized: " + resourceType + " " + resourceName);
	}
}