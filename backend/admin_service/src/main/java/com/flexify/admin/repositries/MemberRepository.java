package com.flexify.admin.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    
 // Fetch member by user id (uid)
    Optional<Member> findByUid(Integer uid);
    
    @Query(value = """
            SELECT 
                CONCAT(u.fname, ' ', u.lname) AS memberName,
                t.uid AS trainerUserId,
                tu.uname AS trainerName,
                ts.description AS specialization,
                pt.tr_id AS trainingId
            FROM member_membership mm
            JOIN members m ON mm.member_id = m.mid
            JOIN users u ON m.uid = u.uid
            JOIN plan_training pt ON pt.plan_id = mm.plan_id
            JOIN trainer_specialization ts ON ts.tr_id = pt.tr_id
            JOIN trainers t ON ts.tid = t.tid
            JOIN users tu ON t.uid = tu.uid
            WHERE mm.plan_id = :planId
        """, nativeQuery = true)
        List<Object[]> findMembersWithTrainingsAndTrainers(@Param("planId") int planId);
}
