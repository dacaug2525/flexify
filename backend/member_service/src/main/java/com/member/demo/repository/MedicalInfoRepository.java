package com.member.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.demo.entities.MedicalInfo;

@Repository
public interface MedicalInfoRepository extends JpaRepository<MedicalInfo, Integer> {

}
