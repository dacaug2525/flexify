package com.flexify.member.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "member_attendence")
public class MemberAttendance {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer attendenceId;

	    @Column(nullable = false)
	    private Integer mid;

	    @Column(nullable = false)
	    private LocalDateTime date;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Status status;

	    public enum Status {
	        PRESENT,
	        ABSENT
	    }
	}


