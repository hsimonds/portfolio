package com.portfolio.profile;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.project.*;
import com.portfolio.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "github")
	private String github;
	
	@Column(name = "linkedin")
	private String linkedin;
	
	@Column(name = "youtube")
	private String youtube;
	
	@Column(name = "personal_website")
	private String personalWebsite;
	
	@Column(name = "other_website")
	private String otherWebsite;	
	
	private Set<String> languages = new HashSet<>();
	
	private Set<String> frameworks = new HashSet<>();
	
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade=CascadeType.ALL,
			orphanRemoval = true)
	private Set<Project> projects = new HashSet<>();
	
	@OneToOne(mappedBy="profile")
	@JsonIgnore
	private User user;
	
	public Profile() {}
	
	public Profile(String name, String github, String linkedin, String youtube,
			String personalWebsite, String otherWebsite) {
		this.name = name;
		this.github = github;
		this.linkedin = linkedin;
		this.youtube = youtube;
		this.personalWebsite = personalWebsite;
		this.otherWebsite = otherWebsite;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}

	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}

	public String getOtherWebsite() {
		return otherWebsite;
	}

	public void setOtherWebsite(String otherWebsite) {
		this.otherWebsite = otherWebsite;
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<String> languages) {
		this.languages.clear();
		if (languages != null) {
			this.languages.addAll(languages);
		}
	}

	public Set<String> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(Set<String> frameworks) {
		this.frameworks.clear();
		if (frameworks != null) {
			this.frameworks.addAll(frameworks);
		}
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}