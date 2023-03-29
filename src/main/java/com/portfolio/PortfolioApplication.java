package com.portfolio;

import com.portfolio.model.Profile;
import com.portfolio.model.Project;
import com.portfolio.model.User;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.portfolio.repository.ProfileRepository;
import com.portfolio.repository.UserRepository;

@SpringBootApplication(scanBasePackages= {"com.*"})
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadUsers(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder encoder) {		
		//Create a couple test users
		User user1 = new User("hasimonds", "heath", "simonds", "heath.simonds@gmail.com", encoder.encode("123"), "USER");
		User user2 = new User("jdoe", "jane", "doe", "jane.doe@example.com", encoder.encode("123"), "USER");
		
		//Create test language sets
		Set<String> langSet = new HashSet<String>();
		langSet.add("Java");
		langSet.add("Python");
		langSet.add("HTML");
		langSet.add("Groovy");
		langSet.add("PHP");
		langSet.add("CSS");
		
		Set<String> langSet2 = new HashSet<String>();
		langSet2.add("Ruby");
		langSet2.add("Rust");
		langSet2.add("C++");
		
		
		//Create test framework sets
		Set<String> frameSet = new HashSet<String>();
		frameSet.add("Spring");
		frameSet.add("React");
		frameSet.add("Django");
		
		Set<String> frameSet2 = new HashSet<String>();
		frameSet2.add("Rails");
		frameSet2.add("Unreal");
		
		//Set some user test profiles
		Profile newProfile1 = new Profile("Heath", "https://github.com/hsimonds", "https://www.linkedin.com/in/heath-simonds/", "myWebsite.com", "myYoutube.com");
		user1.setProfile(newProfile1);
		newProfile1.setLanguages(langSet);
		newProfile1.setFrameworks(frameSet);
		
		Profile newProfile2 = new Profile("Jane", "https://github.com/", "https://www.linkedin.com/", "janeDoeWebsite.sampleData.com", "janesStackOverflow.com");
		user2.setProfile(newProfile2);
		newProfile2.setLanguages(langSet2);
		newProfile2.setFrameworks(frameSet2);
		
		// Create some test projects for first sample user
		Project newProject = new Project("Spring Boot Portfolio Application", "https://github.com/hsimonds/portfolio", "project1.samplesite.com", 
				"A simple portfolio building application built using Spring Boot and Thymeleaf. The application allows users to register for an account and create a "
				+ "developer portfolio showcasing their skills, projects, and professional websites.", "Setting up web security ended up being a bit of a challenge. Not necessarily due to "
						+ "the complexity of the code or setup itself, but due to a large volume of tutorials and examples using deprecated methods for configuring security. Extra time had to be "
						+ "spent reading official spring documentation and reviewing release notes to understand why the majority of tutorials method of handling security was no long supported", 
						"Thymeleaf, HTML, CSS", "Spring Boot, SQL");
		Project newProject2 = new Project("Django Recipe Application", "https://github.com/hsimonds/django-recipes", "project2.samplesite.com", "A recipe application built using Django framework."
				+ " App allows users to create accounts and then build recipes. Recipes are publicly stored in a searchable database where any use can view them.",
				"Nothing of particular note. This was not my first time using the Django framework and similar to previous experience the framework was extremely easy to work with and project development went smoothly",
				"Django, HTML, CSS", "Django, Python");
		Project newProject3 = new Project("Personal Website", "https://github.com/", "project3.samplesite.com", "Personal website providing details on my development experience and links to my profressional websites", 
				"None", "React", "Node.js");
		
		
		// Create some test projects for first sample user
		Project newProject4 = new Project("Jane's unreal game", "https://github.com/", "project4.samplesite.com", 
				"A platformer build in unreal engine", "Creation of graphic assets", 
						"Unreal", "Unreal, C++");
		Project newProject5 = new Project("Jane's first ruby on rails project", "https://github.com/", "project5.samplesite.com", "ToDo list application built using rails",
				"N/A", "HTML,CSS,Javascript", "Rails,Ruby");
		Project newProject6 = new Project("Jane's rust project", "https://github.com/", "project6.samplesite.com", "Small project to test out the rust programming language.", 
				"N/A", "HTML,CSS,Javascript", "Rust");
		
		//Update test profiles with projects
		newProject.setProfile(newProfile1);
		newProject2.setProfile(newProfile1);
		newProject3.setProfile(newProfile1);
		newProfile1.getProjects().add(newProject);
		newProfile1.getProjects().add(newProject2);
		newProfile1.getProjects().add(newProject3);
		
		newProject4.setProfile(newProfile2);
		newProject5.setProfile(newProfile2);
		newProject6.setProfile(newProfile2);
		newProfile2.getProjects().add(newProject4);
		newProfile2.getProjects().add(newProject5);
		newProfile2.getProjects().add(newProject6);
		
		//Save test data
		return args -> {
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
