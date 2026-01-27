package com.member.demo.entities;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Member {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    int mid;
	    int uid;
        LocalDate dob;
	    int height;
	    int weight;
	    String address;
	    
	    LocalDate join_Date;
	    String status;
	  
	    @JsonIgnoreProperties({"medInfos"})
	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	    List<MedicalInfo> medInfos;
	  
}
