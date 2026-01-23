package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginDTO;
import com.example.demo.entities.User;
import com.example.demo.service.LoginService;

@RestController
@RequestMapping("/flexify")
public class LoginController {
	
	@Autowired
	LoginService lservice;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto){
		User user = lservice.login(dto);
		
		return ResponseEntity.ok(user);
	}
}
