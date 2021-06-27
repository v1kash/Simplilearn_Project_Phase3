package com.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.models.User;
import com.ecommerce.repository.UserRepository;


@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("all")
	public List<User> getAllUsers()
	{
		List<User> user=(List<User>) userRepo.findAll(); 
		return user;
	}
	
	@PostMapping("add")
	public User addUser(@RequestBody User user)
	{
		return userRepo.save(user);
	}
	
	@GetMapping("user/{id}")
	public Optional<User> getUsertId(@PathVariable int id)
	{
		return userRepo.findById(id);
	}
	
//	@PutMapping("update/{id}")
//	public User updateUser(@RequestBody User user)
//	{
//		return userRepo.save(user);
//	}
//	
//	@DeleteMapping("delete/{id}")
//	public void deleteUser(@PathVariable int id)
//	{
//		userRepo.deleteById(id);
//	}
}
