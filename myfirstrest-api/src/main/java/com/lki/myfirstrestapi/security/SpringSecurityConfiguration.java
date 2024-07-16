package com.lki.myfirstrestapi.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

	/*
	 * all urls are protected a login form is shwon for unauthorized requests csrf
	 * disable frames
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 * when overriding default SecurityChain -> renew its behavior:
		 */
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// TODO for Rest API -> no Login Page
		http.httpBasic(Customizer.withDefaults());
		/*
		 * type in your behavior
		 */

		http.csrf().disable(); // POST or PUT
		http.headers().frameOptions().disable();

		return http.build();
	}

}
