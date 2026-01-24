package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRegistrationDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	 UserRepository userrepo;

	 @Autowired
	 RoleRepository rolerepo;
	 
	 @Autowired
	 BCryptPasswordEncoder passwordEncoder;

	 //Registration
	 public User registerUser(UserRegistrationDTO dto) {
		// Check email
	    if (userrepo.existsByEmail(dto.getEmail())) {
	         throw new RuntimeException("Email already registered");
	    }

	    // Check username
	    if (userrepo.existsByUname(dto.getUname())) {
	         throw new RuntimeException("Username already taken");
	    }

	    Role role = rolerepo.findById(dto.getRoleid())
                .orElseThrow(() -> new RuntimeException("Invalid Role ID"));
	    	    
	    User user = new User();
	    user.setUname(dto.getUname());
	    
	    user.setPassword(passwordEncoder.encode(dto.getPassword())); // later encrypt
	    user.setFname(dto.getFname());
	    user.setLname(dto.getLname());
	    user.setEmail(dto.getEmail());
	    user.setContact(dto.getContact());
	    user.setGender(dto.getGender());
	    user.setRole(role);
	    return userrepo.save(user);
	 }
	 
	 
	 //findById()
	 public User getOneUser(int id) {
		 User user = null;
		 
		 Optional<User> option = userrepo.findById(id);
		 try {
			 user = option.get();
		 }
		 catch(NoSuchElementException e) {
			 e.printStackTrace();
		 }
		 return user;
	 }
 
	 //GetAllUsers
	 public List<User> getAll(){
		 return userrepo.findAll();
	 }
}
