package com.flexify.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.AdminDashboardDTO;
import com.flexify.admin.repositries.UserRepository;

@Service
public class AdminDashboardService {
	@Autowired
    private UserRepository userRepository;

    public AdminDashboardDTO getDashboardStats() {

    	Integer totalTrainers = (int) userRepository.countByRid(2);
    	Integer totalMembers = (int) userRepository.countByRid(3);

        return new AdminDashboardDTO(
                totalTrainers,
                totalMembers
        );
    }
}
