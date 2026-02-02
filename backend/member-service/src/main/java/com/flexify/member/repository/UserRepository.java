package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexify.member.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
