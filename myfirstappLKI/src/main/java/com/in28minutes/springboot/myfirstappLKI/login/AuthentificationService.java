package com.in28minutes.springboot.myfirstappLKI.login;

import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
	
	public boolean authenticate(String user, String password) {
		boolean login = user.equals("name1");
		login = login&& password.equals("dummy");
		return login;
	}

}
