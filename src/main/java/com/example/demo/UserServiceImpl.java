package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRep;

	@Override
	public User saveUser(User user) {
		

		return userRep.save(user);

	}

	@Override
	public List<User> retriveAllUsers() {
		
		return userRep.findAll();
	}

	@Override
	public User findUser(int id) {
		
		return userRep.findById(id).get();
	}

	@Override
	public void updation(User user) {
		user.setIsactive(false);
		userRep.save(user);

	}

	@Override
	public void hardDeleteUser(int id) {
		userRep.deleteById(id);
	}

	@Override
	public List<User> getAllUserByName(String userName) {
		return userRep.findByUserName(userName);
	}

	@Override
	public List<User> sortByDateOfBirth() {
		return userRep.findAll(Sort.by("dateOfBirth"));
	}

	@Override
	public List<User> sortByJoiningDate() {
		return userRep.findAll(Sort.by("joiningDate"));
	}

	@Override
	public Optional<User> getuser(int id) {
		return userRep.findByUserId(id);
	}

	public List<User> getAllUserByLastName(String lastName) {
		
		 return userRep.findBySurname(lastName);
	}

}
