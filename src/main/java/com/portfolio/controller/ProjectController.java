package com.portfolio.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.service.ProjectService;

@Controller
public class ProjectController {
	private final ProjectRepository projectRepository;
	private final ProjectService projectService;
	
	public ProjectController (ProjectRepository projectRepository, ProjectService projectService) {
		this.projectRepository = projectRepository;
		this.projectService = projectService;
	}
	
	@GetMapping("/project")
	public String showCreateProjectForm(Model model, Principal principal) {
		Project project = new Project();
		model.addAttribute(project);
		return "create-project";
	}
	
	@PostMapping("/project")
	public String createProject(@ModelAttribute("project") Project project, Model model, Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return "create-project";
		}
		projectService.createProject(project, principal);
		model.addAttribute("message", "Project created successfully");
		return "edit-project";
		
	}
	
	@GetMapping("/project/{id}")
	public String showEditProjectForm(@PathVariable Long id, Model model, Principal principal) {
		Optional<Project> project = projectService.findProjectById(id);
		if (project.isPresent()) {
			model.addAttribute("project", project.get());
			return "edit-project";
		}
		else {
			model.addAttribute("message", "Project not found");
			return "index";
		}
	}
	
	@PostMapping("/project/{id}")
	public String editProject(@ModelAttribute("project") Project project, Model model, Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return "edit-project";
		}
		projectService.updateProject(project, principal);
		model.addAttribute("message", "Project updated successfully");
		return "edit-project";
	}
	
	@DeleteMapping("/project/{id}")
	public String deleteProject(@PathVariable Long id, Model model) {
		projectRepository.deleteById(id);
		model.addAttribute("message", "Project deleted successfully");
		return "redirect:/profile";
	}
}

