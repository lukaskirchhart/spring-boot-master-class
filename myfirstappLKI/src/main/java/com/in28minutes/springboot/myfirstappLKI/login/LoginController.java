package com.in28minutes.springboot.myfirstappLKI.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private AuthentificationService authentification;
	
	public LoginController(AuthentificationService authentification) {
		this.authentification=authentification;
	}

	//GET, POST
	@GetMapping("login")
	public String loginGet() {
//		model.put("name", name);
//		logger.debug("Request param is {}",name);
		
		
		//open META-INF/resources/WEB-INF/jsp/login.jsp
		//Open *.jsp is defined in application.properties
		return "login";
	}
	
	@PostMapping("login")
	public String gotoWelcomePage(@RequestParam String name,  @RequestParam String password, ModelMap model) {
		model.put("name", name);
		model.put("password", password);
		
		
		authentification = new AuthentificationService();
		if(authentification.authenticate(name, password)) {			
			return "welcome";
		}else {
			model.put("loginFailed", "Invalid credentials. Please try again");
			return "login";
		}
		
	}

}
