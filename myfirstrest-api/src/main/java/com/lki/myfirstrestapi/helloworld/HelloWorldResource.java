package com.lki.myfirstrestapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloWorldResource {

//	@ResponseBody <- not needed, because @RestController
	@RequestMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello Bean");
	}

	// Path Variable or Path Params
	// /user/Lukas/todos/1

	@RequestMapping("/hello-world-path-param/{name}")
	public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
		return new HelloWorldBean("Hello World, %s".formatted(name));
	}

	@RequestMapping("/hello-world-path-param/{name}/message/{message}")
	public HelloWorldBean helloWorldMultiplePathParam(@PathVariable String name, @PathVariable String message) {
		return new HelloWorldBean("%s %s".formatted(message, name));
	}
}
