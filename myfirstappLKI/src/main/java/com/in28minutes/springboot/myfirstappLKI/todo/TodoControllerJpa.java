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
public class TodoControllerJpa {

	private static final String LIST_TODOS_JSP = "listTodos";
	private static final String LIST_TODOS_URL = "list-todos";
	private static final String REDIRECT_TO_LIST_TODOS_URL = "redirect:" + LIST_TODOS_URL;
	private static final String TODO_JSP = "todo";
	private TodoService todoService;

	private TodoReposity todoRepository;

	public TodoControllerJpa(TodoService service, TodoReposity todoRepo) {
		this.todoService = service;
		this.todoRepository = todoRepo;
	}

	@RequestMapping(LIST_TODOS_URL)
	public String listAllTodos(ModelMap model) {
		List<Todo> usersTodos = this.todoService.findByUserName(getLoggedInUserName());

		usersTodos = todoRepository.findByUsername(getLoggedInUserName());

		model.put("todos", usersTodos);
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
		todo.setUsername(userName);
		todoRepository.save(todo);
//		todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return REDIRECT_TO_LIST_TODOS_URL;
	}

	private String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
//		this.todoService.deleteTodo(id);
		return REDIRECT_TO_LIST_TODOS_URL;
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String udpateTodo(@RequestParam int id, ModelMap model) {
		Todo editTodo = todoRepository.findById(id).get();
//		Todo editTodo = todoService.findById(id);
		model.addAttribute("todo", editTodo);
		return TODO_JSP;
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String udpateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TODO_JSP;
		}
		todoRepository.save(todo);
//		todoService.updateTodo(todo.getId(), todo);
		return REDIRECT_TO_LIST_TODOS_URL;
	}

}
