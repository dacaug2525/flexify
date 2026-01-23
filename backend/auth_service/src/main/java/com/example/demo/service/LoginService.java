package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.LoginDTO;
import com.example.demo.entities.User;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {
 
	@Autowired
	LoginRepository loginrepo;

	public User login(LoginDTO dto) {
		User user =loginrepo.findByEmail(dto.getEmail()).orElseThrow(()-> new RuntimeException("Invalid Email"));
		
		if(!user.getPassword().equals(dto.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		return user;
	}
	
}
