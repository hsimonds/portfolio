package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByDisplayName(String displayName);
	
}