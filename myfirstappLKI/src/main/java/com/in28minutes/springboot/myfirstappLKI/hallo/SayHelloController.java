package com.in28minutes.springboot.myfirstappLKI.hallo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {

	
	//say hello
	@RequestMapping("say-hello")
	public String sayHello() {
		return "Hello! What are you learning today";
	}
	
	@RequestMapping("say-hello-html")
	public String sayHelloHtml() {
		return """
			<html>
				<head>
					<title>my title </title>
				</head>
				<body>my body</body>
			</html>
				""";
	}
	
	//JSP
	//"say-hello-html") ->sayHello.jsp
	///myfirstappLKI/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
	
}
