package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.services.UserService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class UserController {
	@Autowired
	UserService userv;
	
	// MEMBER LIST
    @GetMapping("/allmembers/{rid}")
    public List<UserEntity> getAllMembers(@PathVariable Integer rid) {
        return userv.getAllMembers(rid);
    }
    
 // MEMBER LIST
    @GetMapping("/alltrainers/{rid}")
    public List<UserEntity> getAllTrainers(@PathVariable Integer rid) {
        return userv.getAllTrainers(rid);
    }
}
