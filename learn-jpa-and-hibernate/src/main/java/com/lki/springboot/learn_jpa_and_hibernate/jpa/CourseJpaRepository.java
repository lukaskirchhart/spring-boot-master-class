package com.lki.springboot.learn_jpa_and_hibernate.jpa;

import org.springframework.stereotype.Repository;

import com.lki.springboot.learn_jpa_and_hibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Course course) {
		entityManager.merge(course);
	}

	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(long id) {
		Course byId = findById(id);
		entityManager.remove(byId);
	}
}