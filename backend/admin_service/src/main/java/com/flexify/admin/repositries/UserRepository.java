package com.flexify.admin.repositries;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	 	
		long countByRid(Integer rid);

		List<UserEntity> findByRid(int i);		
		
}
