package com.example.demo;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Import(UserServiceImpl.class)
@RunWith(SpringRunner.class)
public class apitest {
	
	
	@Autowired
	UserServiceImpl service;

	@Before
	public void setUp() throws Exception {
	}

	
	
	
	@Test
	public void testFindAll() {
	    List<User> foundUsers = service.retriveAllUsers();

	    assertNotNull(foundUsers);
	    assertEquals(service.retriveAllUsers().size(), foundUsers.size());
	}
	
	



	@Test
	public void testAddUser() {
		User user= new User();
		user.setAddress("pune");
		user.setDateOfBirth(new Date(2012-11-11));
		user.setJoiningDate(new Date(2014-11-11));
		user.setPincode(121212);
		user.setSurname("chavhan");
		user.setUserName("bharaat");
		user.setIsactive(true);
		
		User u1=service.saveUser(user);
		assertEquals(u1, user);
	}

	@Test
	public void testGetUserInt() {
		User user =service.findUser(20);
		assertTrue("Bharat99999".equals(user.getUserName()));
		
	}

	@Test
	public void testUpdateUser() {
		User user =service.findUser(20);
		user.setAddress("yavatmal");
		service.saveUser(user);
		User updateUser =service.findUser(20);
		assertEquals(user.getAddress(), updateUser.getAddress());

		
	}

	@Test
	public void testDeleteUser() {
//		User user =service.findUser(16);
//		userRepo.delete(user);
//		assertNull(service.findUser(16));
		service.hardDeleteUser(12);
		Assert.assertNull(service.getuser(12));
	}

	@Test
	public void testGetUserbyname() {
		 List<User> Users = service.getAllUserByName("Bharat99999");
		  //assertNotNull(Users);
		User user=service.findUser(20);
		  assertEquals(Users.get(0).getUserName(), user.getUserName());
		 
	}

	@Test
	public void testGetUserSorted() {
		
	}

	
	@Test
	public void testSoftDelete() {
		User user =service.findUser(20);
		
		service.updation(user);
		assertFalse(user.isIsactive());
	}

}
