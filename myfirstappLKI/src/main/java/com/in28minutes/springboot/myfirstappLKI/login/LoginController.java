package com.in28minutes.springboot.myfirstappLKI.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("login")
	public String login(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		logger.debug("Request param is {}",name);
		//open META-INF/resources/WEB-INF/jsp/login.jsp
		//Open *.jsp is defined in application.properties
		return "login";
	}

}
