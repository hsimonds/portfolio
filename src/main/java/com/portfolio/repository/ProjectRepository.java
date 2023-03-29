package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	Optional<Project> findByName(String name);
}