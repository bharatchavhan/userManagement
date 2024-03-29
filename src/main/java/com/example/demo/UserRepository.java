package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(int uid);

	public List<User> findAll();

	List<User> findByUserName(String userName);

	List<User> findBySurname(String lastName);

	
}
