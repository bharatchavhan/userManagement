package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.example.demo.User;

public interface UserService {

	User saveUser(User user);

	List<User> retriveAllUsers();
	
	Optional<User> getuser(int id);

	User findUser(int id);

	void updation(User user);

	void hardDeleteUser(int id);
	
	List<User> getAllUserByName(String userName);
	
	List<User> sortByDateOfBirth();

	List<User> sortByJoiningDate();


}