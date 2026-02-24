package com.in28minutes.rest.webservices.restful_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {

		// authenticate all request
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		//make use of webpage to authenticate a request
		http.httpBasic(withDefaults());
		//enable post and put methods
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
