package com.flexify.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.AdminDashboardDTO;
import com.flexify.admin.dto.UserDTO;
import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.repositries.UserRepository;

@Service
public class AdminDashboardService {
	 @Autowired
	    private UserRepository userRepository;

	    public AdminDashboardDTO getDashboardStats() {
	        long totalTrainers = userRepository.countByRid(2); // Trainer role
	        long totalMembers = userRepository.countByRid(3);  // Member role
	        return new AdminDashboardDTO(totalTrainers, totalMembers);
	    }

	    public List<UserDTO> getTrainerAndMemberUsers() {
	        List<UserEntity> users = userRepository.findUsersWithTrainerAndMemberRole();
	        List<UserDTO> result = new ArrayList<>();

	        for (UserEntity u : users) {
	            String roleName = u.getRid() == 2 ? "Trainer" : "Member";
	            String fullName = u.getFname() + " " + u.getLname();
	            result.add(new UserDTO(u.getUid(), fullName, u.getEmail(), roleName));
	        }
	        return result;
	    }
    
}
