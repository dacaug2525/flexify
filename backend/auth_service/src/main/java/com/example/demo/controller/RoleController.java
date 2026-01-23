package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/flexify")
public class RoleController {
	
	@Autowired
	RoleService service;
	
	
	//getAll role
	@GetMapping("/roles")
	public List<Role> getAll(){
		return service.getAll();
	}
}
