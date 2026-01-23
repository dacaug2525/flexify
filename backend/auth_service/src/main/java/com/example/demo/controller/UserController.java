package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRegistrationDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/flexify")
public class UserController {

	 @Autowired
	 UserService userService;

	 //Register
	 @PostMapping("/register")
	 public ResponseEntity<String> register(@RequestBody UserRegistrationDTO dto) { 
	     userService.registerUser(dto);
	     return ResponseEntity.ok("User registered successfully");
	 }
	
	 
	 //GetAllUsers
	 @GetMapping("/users")
	 public List<User> getAll(){
		 return userService.getAll();
				 
	 }
}
