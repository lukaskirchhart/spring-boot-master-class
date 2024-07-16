package com.lki.myfirstrestapi.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandlineRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UserDetailsRepository repository;

	public UserDetailsCommandlineRunner(UserDetailsRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(Arrays.toString(args));
		repository.save(new UserDetails("Lukas", "Admin"));
		repository.save(new UserDetails("Till", "User"));
		repository.save(new UserDetails("Hill", "Developer"));

		List<UserDetails> users = repository.findByRole("Admin");

		users.forEach(u -> logger.info(u.toString()));
	}

}
