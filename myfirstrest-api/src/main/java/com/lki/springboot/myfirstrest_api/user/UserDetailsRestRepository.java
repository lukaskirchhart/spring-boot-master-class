package com.lki.springboot.myfirstrest_api.user;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource(path)
public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails, Long> {

	List<UserDetails> findByRole(String role);
}
