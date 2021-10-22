package com.example.demo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserService userservice;

	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	static {
		System.out.println("inside User controller");
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";

	}

	@GetMapping("/getall")
	public List<User> getalluser() {
		
		return userservice.retriveAllUsers();

	}

	List<User> users = new ArrayList<User>();

	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		userservice.saveUser(user);
		users.add(user);
		System.out.println(users.size());
		System.out.println(users.toString());
		return userservice.saveUser(user);

	}

	@GetMapping("/{Uid}")
	public User getUser(@PathVariable("Uid") int uid) {
		return userservice.findUser(uid);

	}

	@PostMapping("/update/{Uid}")
	public List<User> updateUser(@PathVariable("Uid") int uid, @RequestBody User user) {

		userservice.updation(user);
		return userservice.retriveAllUsers();

	}

	@DeleteMapping("/delete/{Uid}")
	public List<User> deleteUser(@PathVariable("Uid") int uid) {

		userservice.hardDeleteUser(uid);
		return userservice.retriveAllUsers();

	}

	@GetMapping("/getbysurnanme/{surname}")
	public List<User> getUser(@PathVariable("surname") String sirname) {

		return users.stream().filter(x -> x.getSurname().equalsIgnoreCase(sirname)).collect(Collectors.toList());
	}

	@GetMapping("/getbyname/{userName}")
	public List<User> getUserbyname(@PathVariable("userName") String userName) {
		List<User> u2 = userservice.getAllUserByName(userName);

		return u2;
	}

	@GetMapping("/sortbydate")
	public List<User> getUserSorted() {
		return userservice.sortByDateOfBirth();
	}

	@GetMapping("/sortbyjoiningdate")
	public List<User> getUserSortedbyjoiningdate() {
		return userservice.sortByJoiningDate();

	}

	@PatchMapping("/softdelete/{Uid}")
	public String softdelete(@PathVariable("Uid") int uid) {
		User user = userservice.findUser(uid);
		userservice.updation(user);
		return "user deleted";

	}
}