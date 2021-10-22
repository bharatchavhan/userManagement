package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.UserNotFound;

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
	public ResponseEntity<?> addUser(@RequestBody User user) {

		if (user == null) {
			return new ResponseEntity<>("invalid user type", HttpStatus.OK);
		} else if (user.getUserName() == (null) || user.getPincode() == 0) {
			return new ResponseEntity<>("invalid user attribute", HttpStatus.OK);
		} else {
			userservice.saveUser(user);
			users.add(user);
			return new ResponseEntity<>("added succesfully", HttpStatus.CREATED);
		}
	}

	@GetMapping("/{Uid}")
	public ResponseEntity<?> getUser(@PathVariable("Uid") int uid) throws UserNotFound {
		User user = userservice.findUser(uid);
		if (user == null) {
			return new ResponseEntity<String>("no records with given id", HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@PostMapping("/update/{Uid}")
	public ResponseEntity<?> updateUser(@PathVariable("Uid") int uid, @RequestBody User user) {

		if (user == null) {
			return new ResponseEntity<>("invalid user type", HttpStatus.OK);
		} else if (user.getUserName() == null|| userservice.retriveAllUsers().stream().noneMatch(i -> i.getUserId() == uid)) {
			return new ResponseEntity<>("invalid user attribute", HttpStatus.OK);
		}
		user.setUserId(uid);
		userservice.saveUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{Uid}")
	public ResponseEntity<?> deleteUser(@PathVariable("Uid") int uid) {
		User user = userservice.findUser(uid);
		if (user == null) {
			return new ResponseEntity<>("invalid user type", HttpStatus.OK);
		} else if (userservice.retriveAllUsers().stream().noneMatch(i -> i.getUserId() == uid)) {
			return new ResponseEntity<>("User not present ", HttpStatus.OK);
		}
		userservice.hardDeleteUser(uid);
		return new ResponseEntity<>("sucessfully delete", HttpStatus.OK);

	}

	@GetMapping("/getbylastname/{lastname}")
	public List<User> getUser(@PathVariable("lastname") String sirname) {

		return userservice.getAllUserByLastName(sirname);
	}

	@GetMapping("/getbyname/{userName}")
	public List<User> getUserbyname(@PathVariable("userName") String userName) {
		return userservice.getAllUserByName(userName);
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