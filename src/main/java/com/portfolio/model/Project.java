package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table (name="portfolio_projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "project_name")
	private String name;
	
	@Column(name = "project_github")
	private String github;
	
	@Column(name = "project_website")
	private String website;
	
	@Column(name = "project_description", columnDefinition = "text")	
	private String description;
	
	@Column(name = "project_challenges", columnDefinition = "text")
	private String challenges;
	
	@Column(name = "fe_tech_stack")
	private String frontEndTechStack;
	
	@Column(name = "be_tech_stack")
	private String backEndTechStack;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	@JsonIgnore
	private Profile profile;
	
	public Project() {}
	
	public Project (String name, String github, String website, String description, String challenges,
			String frontEndTechStack, String backEndTechStack) {
		this.name = name;
		this.github = github;
		this.website = website;
		this.description = description;
		this.challenges = challenges;
		this.frontEndTechStack = frontEndTechStack;
		this.backEndTechStack = backEndTechStack;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getFrontEndTechStack() {
		return frontEndTechStack;
	}

	public void setFrontEndTechStack(String frontEndTechStack) {
		this.frontEndTechStack = frontEndTechStack;
	}

	public String getBackEndTechStack() {
		return backEndTechStack;
	}

	public void setBackEndTechStack(String backEndTechStack) {
		this.backEndTechStack = backEndTechStack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}