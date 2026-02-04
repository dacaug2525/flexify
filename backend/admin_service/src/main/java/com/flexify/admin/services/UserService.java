package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.repositries.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository urepo;
	
	public List<UserEntity> getAllMembers(int rid){
		return urepo.findByRid(rid);
	}
	

	public List<UserEntity> getAllTrainers(int rid){
		return urepo.findByRid(rid);
	}
	
}
