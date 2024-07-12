package com.in28minutes.springboot.myfirstappLKI.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

	// LDAP or Database
	// in Memory

	@Bean
	public InMemoryUserDetailsManager createUserDetailManager() {

		UserDetails userDetails = createNewUser("user1", "dummy");
		UserDetails userDetails2 = createNewUser("user2", "dummy2");
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userDetails,
				userDetails2);
		return inMemoryUserDetailsManager;
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
