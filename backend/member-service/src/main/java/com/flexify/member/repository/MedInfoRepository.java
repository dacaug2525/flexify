package com.flexify.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.MedInfo;
@Repository
public interface MedInfoRepository extends JpaRepository<MedInfo, Integer> {
	List<MedInfo> findByMemberMid(Integer mid);
}
