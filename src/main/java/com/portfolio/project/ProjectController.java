package com.portfolio.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.profile.*;



@Controller
public class ProjectController {
	private final ProjectRepository projectRepository;
	private final ProfileRepository profileRepository;
	
	public ProjectController (ProjectRepository projectRepository, ProfileRepository profileRepsository) {
		this.projectRepository = projectRepository;
		this.profileRepository = profileRepsository;
	}
	
	@GetMapping("/project/create")
	public String showCreateProjectForm(Model model) {
		Project project = new Project();
		model.addAttribute(project);
		return "create-project";
	}
	
	@GetMapping("/project/{id}")
	public String showProjectEditForm(@PathVariable Long id, Model model) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException(id));
		model.addAttribute(project);
		return "edit-profile";
	}
	
	@PostMapping("/project/{profile_id}")
	public Project newProject(@PathVariable Long profile_id,
			@RequestBody Project project) {
		Profile profile = profileRepository.findById(profile_id)
				.orElseThrow(() -> new ProfileNotFoundException(profile_id));
		project.setProfile(profile);
		Project newProject = projectRepository.save(project);
		return newProject;
	}
	
	@PutMapping("/project/{id}")
	public Project updateProject(@PathVariable Long id, @RequestBody Project newProject) {
		Project updatedProject = projectRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException(id));
		
		updatedProject.setGithub(newProject.getGithub());
		updatedProject.setWebsite(newProject.getWebsite());
		updatedProject.setDescription(newProject.getDescription());
		updatedProject.setChallenges(newProject.getChallenges());
		updatedProject.setFrontEndTechStack(newProject.getFrontEndTechStack());
		updatedProject.setBackEndTechStack(newProject.getBackEndTechStack());
		
		return projectRepository.save(updatedProject);
	}
	
	@DeleteMapping("/project/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectRepository.deleteById(id);
	}
}

