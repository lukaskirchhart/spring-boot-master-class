package com.in28minutes.springboot.myfirstappLKI.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService totoService;

	public TodoController(TodoService service) {
		this.totoService = service;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> byUserName = this.totoService.findByUserName("user1");
		model.put("todos", byUserName);
		return "listTodos";
	}

}
