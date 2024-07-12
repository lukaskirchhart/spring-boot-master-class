package com.lki.springboot.learn_jpa_and_hibernate.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lki.springboot.learn_jpa_and_hibernate.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

	public List<Course> findByAuthor(String author);

	// SPring JPA eraet was hiermit gemeint ist und interpretiert den Code
	public List<Course> findByName(String name);
}
