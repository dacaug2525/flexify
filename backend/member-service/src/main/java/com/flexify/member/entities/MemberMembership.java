package com.flexify.member.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "member_membership")
public class MemberMembership {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer membershipId;

	    @NotNull
	    private Integer memberId; // FK from members table

	    @ManyToOne
	    @JoinColumn(name = "plan_id")
	    private Plan plan;

	    @NotNull
	    private LocalDate startDate;

	    @NotNull
	    private LocalDate endDate;

	    @NotNull
	    @Enumerated(EnumType.STRING)
	    private Status status;

	    public enum Status { ACTIVE, INACTIVE }

	    // getters & setters
	}


