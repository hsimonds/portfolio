package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf().disable()
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(PathRequest.toH2Console()).permitAll()
						.requestMatchers("/register").permitAll()
						.requestMatchers("/").permitAll()
						.requestMatchers("/profile/**").permitAll()
						.requestMatchers("/user/create").permitAll()
						.requestMatchers("/error/**").permitAll()
						.requestMatchers("/static/**").permitAll()
						.requestMatchers("/resources/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.anyRequest().authenticated())
				.userDetailsService(userDetailsService)
				.headers(headers -> headers.frameOptions().sameOrigin())
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
						.defaultSuccessUrl("/",true)
					)
				.logout((logout) -> logout.permitAll())
				.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}