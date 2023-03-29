package com.portfolio.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.exception.NotAuthorizedException;
import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	
	private UserService userService;
	
	public ProjectService(ProjectRepository projectRepository, UserService userService) {
		this.projectRepository = projectRepository;
		this.userService = userService;
	}
	
	public Project createProject(Project project, Principal principal) {
		project.setProfile(userService.findUserByUsername(principal.getName()).get().getProfile());
		return projectRepository.save(project);
	}
	
	public Optional<Project> updateProject(Project project, Principal principal) {
		Optional<Project> foundProject = findProjectById(project.getId());
		if (foundProject.isPresent()) {
			Project existingProject = foundProject.get();
			if (!(existingProject.getProfile().getUser().getUsername().equals(principal.getName()))) {
				throw new NotAuthorizedException("project", project.getName());
			}
			existingProject.setName(project.getName());
			existingProject.setGithub(project.getGithub());
			existingProject.setWebsite(project.getWebsite());
			existingProject.setDescription(project.getDescription());
			existingProject.setChallenges(project.getChallenges());
			existingProject.setFrontEndTechStack(project.getFrontEndTechStack());
			existingProject.setBackEndTechStack(project.getBackEndTechStack());
			projectRepository.save(existingProject);
		}
		return foundProject;
	}
	
	public Optional<Project> findProjectById(Long id) {
		return projectRepository.findById(id);
	}
	
	public void deleteProject(Project project, Principal principal) {
		if (project.getProfile().getUser().getUsername().equals(principal.getName())) {
			projectRepository.deleteById(project.getId());	
		}
	}
	
}