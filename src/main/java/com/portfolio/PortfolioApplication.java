package com.portfolio;

import com.portfolio.profile.*;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.portfolio.project.Project;
import com.portfolio.user.User;
import com.portfolio.user.UserRepository;



@SpringBootApplication(scanBasePackages= {"com.*"})
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadUsers(UserRepository userRepository, ProfileRepository profileRepository) {
		User user1 = new User("hasimonds", "heath", "simonds", "heath.simonds@gmail.com", "Password123");
		User user2 = new User("jdoe", "jane", "doe", "jane.doe@example.com", "Password123");
		Set<String> langSet = new HashSet<String>();
		langSet.add("Java");
		langSet.add("Python");
		Profile newProfile1 = new Profile("Heath", "https://github.com/hsimonds", "https://www.linkedin.com/in/heath-simonds/", "https://www.youtube.com/channel/UCC2U3GjIRcPKbjwDYg3R7yQ", "", "");
		user1.setProfile(newProfile1);
		newProfile1.setLanguages(langSet);
		Profile newProfile2 = new Profile("Jane", "https://github.com/jdoe", "https://www.linkedin.com/in/jane-doe/", "https://www.youtube.com/channel/jane", "", "");
		user2.setProfile(newProfile2);
		Set<String> springFrame = new HashSet<String>();
		springFrame.add("Spring");
		newProfile1.setFrameworks(springFrame);
		Project newProject = new Project("proj.github", "proj.website", "My first project", "everything", "react", "node.js");
		newProject.setProfile(newProfile1);
		newProfile1.getProjects().add(newProject);
		return args -> {
			userRepository.save(user1);
			userRepository.save(user2);
			//profileRepository.save(newProfile1);
		};
	}
	
}
