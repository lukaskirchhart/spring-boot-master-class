package com.in28minutes.springboot.myfirstappLKI.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "user1", "learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "user1", "learn Devops", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(3, "user1", "learn blub", LocalDate.now().plusYears(1), false));
	}

	public List<Todo> findByUserName(String userName) {
		return todos;
	}
}
