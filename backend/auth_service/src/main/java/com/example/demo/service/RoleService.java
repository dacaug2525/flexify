package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository rolerepo;
	
	public List<Role> getAll(){
		return rolerepo.findAll();
	}
}
