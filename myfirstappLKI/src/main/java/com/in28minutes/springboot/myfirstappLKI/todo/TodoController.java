package com.in28minutes.springboot.myfirstappLKI.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {

	private static final String LIST_TODOS_JSP = "listTodos";
	private static final String LIST_TODOS_URL = "list-todos";
	private static final String REDIRECT_TO_LIST_TODOS_URL = "redirect:" + LIST_TODOS_URL;
	private static final String TODO_JSP = "todo";
	private TodoService todoService;

	public TodoController(TodoService service) {
		this.todoService = service;
	}

	@RequestMapping(LIST_TODOS_URL)
	public String listAllTodos(ModelMap model) {
		List<Todo> byUserName = this.todoService.findByUserName(getLoggedInUserName());
		model.put("todos", byUserName);
		return LIST_TODOS_JSP;
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodo(ModelMap model) {
		String userName = getLoggedInUserName();
		Todo todo = new Todo(0, userName, "default description", LocalDate.now(), false);
		model.put(TODO_JSP, todo);
		return TODO_JSP;
	}

//	@PostMapping("add-todo")
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return TODO_JSP;
		}

		String userName = getLoggedInUserName();
		todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);
		return REDIRECT_TO_LIST_TODOS_URL;
	}

	private String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		this.todoService.deleteTodo(id);
		return REDIRECT_TO_LIST_TODOS_URL;
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String udpateTodo(@RequestParam int id, ModelMap model) {
		Todo editTodo = todoService.findById(id);
		model.addAttribute("todo", editTodo);
		return TODO_JSP;
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String udpateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TODO_JSP;
		}
		todoService.updateTodo(todo.getId(), todo);
		return REDIRECT_TO_LIST_TODOS_URL;
	}

}
