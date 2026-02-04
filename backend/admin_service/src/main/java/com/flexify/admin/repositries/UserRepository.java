package com.flexify.admin.repositries;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
		
		// total by role
	    long countByRid(Integer rid);

	    // pending members (rid = 3 but uid not in members)
	    @Query("""
	        SELECT COUNT(u)
	        FROM UserEntity u
	        WHERE u.rid = 3
	        AND u.uid NOT IN (SELECT m.uid FROM Member m)
	    """)
	    long countPendingMembers();

	    
	    
	    // pending trainers (rid = 2 but uid not in trainers)
	    @Query("""
	        SELECT COUNT(u)
	        FROM UserEntity u
	        WHERE u.rid = 2
	        AND u.uid NOT IN (SELECT t.uid FROM Trainer t)
	    """)
	    long countPendingTrainers();

	    // list users with role 2 & 3 only
	    @Query("""
	        SELECT u FROM UserEntity u
	        WHERE u.rid IN (2, 3)
	    """)
	    List<UserEntity> findUsersWithTrainerAndMemberRole();
	    
	    
	    
	    @Query("""
	    		SELECT u FROM UserEntity u 
	    		WHERE u.rid IN (2,3)
	    		""")
	    List<UserEntity> findTrainersAndMembers();

	    List<UserEntity> findByRid(int rid);
}
