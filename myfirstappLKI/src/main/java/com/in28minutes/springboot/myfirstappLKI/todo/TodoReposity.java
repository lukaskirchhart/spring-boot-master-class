package com.in28minutes.springboot.myfirstappLKI.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoReposity extends JpaRepository<Todo, Integer> {

	public List<Todo> findByUsername(String userName);
}
