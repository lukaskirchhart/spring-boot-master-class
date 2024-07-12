package com.lki.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lki.springboot.learn_jpa_and_hibernate.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandlineRunner implements CommandLineRunner {

//	@Autowired
//	CourseJdbcRepository repository;

//	
//	@Autowired
//	CourseJpaRepository repository;

	@Autowired
	CourseSpringDataJpaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn JDBC", "Lukas"));
		repository.save(new Course(2, "Learn Spring Boot", "Lukas 2"));
		repository.save(new Course(3, "Learn Cloud", "Lukas 3"));

		repository.deleteById(1l);

		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));

		System.out.println(repository.findAll());
		System.out.println(repository.findByAuthor("Lukas 2"));
		System.out.println(repository.findByName("Learn Cloud"));

//		repository.insert(new Course(1, "Learn JDBC", "Lukas"));
//		repository.insert(new Course(2, "Learn Spring Boot", "Lukas 2"));
//		repository.insert(new Course(3, "Learn Cloud", "Lukas 3"));
//		
//		repository.deleteById(1);
//		
//		System.out.println(repository.findById(2));
//		System.out.println(repository.findById(3));
	}

}
