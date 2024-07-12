package com.in28minutes.springboot.myfirstappLKI.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	// GET, POST
	@GetMapping("/")
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
//		logger.debug("Request param is {}",name);

		// open META-INF/resources/WEB-INF/jsp/login.jsp
		// Open *.jsp is defined in application.properties
		return "welcome";
	}

	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
