package com.flexify.admin.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_membership")
public class MemberMembership {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "membership_id")
	    private Integer membershipId;

	    @Column(name = "member_id", nullable = false)
	    private Integer memberId;

	    @Column(name = "plan_id", nullable = false)
	    private Integer planId;

	    @Column(name = "start_date", nullable = false)
	    private LocalDateTime startDate;

	    @Column(name = "end_date", nullable = false)
	    private LocalDateTime endDate;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status", nullable = false)
	    private Status status;

	    public enum Status {
	        active,
	        inactive
	    }

		public MemberMembership() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MemberMembership(Integer membershipId, Integer memberId, Integer planId, LocalDateTime startDate,
				LocalDateTime endDate, Status status) {
			super();
			this.membershipId = membershipId;
			this.memberId = memberId;
			this.planId = planId;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
		}

		public Integer getMembershipId() {
			return membershipId;
		}

		public void setMembershipId(Integer membershipId) {
			this.membershipId = membershipId;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public Integer getPlanId() {
			return planId;
		}

		public void setPlanId(Integer planId) {
			this.planId = planId;
		}

		public LocalDateTime getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDateTime startDate) {
			this.startDate = startDate;
		}

		public LocalDateTime getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDateTime endDate) {
			this.endDate = endDate;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}
	    
}
