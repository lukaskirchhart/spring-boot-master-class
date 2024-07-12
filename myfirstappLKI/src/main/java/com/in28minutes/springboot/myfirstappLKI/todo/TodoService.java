package com.in28minutes.springboot.myfirstappLKI.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;

	static {
		LocalDate plusYears = LocalDate.now().plusYears(1);
		todos.add(new Todo(++todosCount, "user1", "learn AWS 1", plusYears, false));
		todos.add(new Todo(++todosCount, "user1", "learn Devops 1", plusYears, false));
		todos.add(new Todo(++todosCount, "user1", "learn blub 1", plusYears, false));
	}

	public List<Todo> findByUserName(String userName) {
		return todos.stream().filter(t -> t.getUsername().equals(userName)).toList();
	}

	public void addTodo(String name, String description, LocalDate targetData, boolean done) {
		Todo todo = new Todo(++todosCount, name, description, targetData, done);
		todos.add(todo);
	}

	public void deleteTodo(int id) {
		todos.removeIf(t -> t.getId() == id);
	}

	public void updateTodo(int id, Todo updatedTodo) {
		todos.stream().filter(t -> t.getId() == id).findFirst().ifPresent(todos::remove);
		todos.add(updatedTodo);
	}

	public Todo findById(int id) {
		return todos.stream().filter(t -> t.getId() == id).findFirst().get();
	}
}
