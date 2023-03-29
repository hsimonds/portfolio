package com.portfolio.exception;


@SuppressWarnings("serial")
public class ProjectNotFoundException extends RuntimeException {
	public ProjectNotFoundException (Long id) {
		super("Project with id: " + id + " not found.");
	}
}