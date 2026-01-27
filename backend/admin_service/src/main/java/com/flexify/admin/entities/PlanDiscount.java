package com.flexify.admin.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plan_discount")
public class PlanDiscount {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer disId;

	    @Column(nullable = false)
	    private Integer duration;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal discount;

		public PlanDiscount() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PlanDiscount(Integer disId, Integer duration, BigDecimal discount) {
			super();
			this.disId = disId;
			this.duration = duration;
			this.discount = discount;
		}

		public Integer getDisId() {
			return disId;
		}

		public void setDisId(Integer disId) {
			this.disId = disId;
		}

		public Integer getDuration() {
			return duration;
		}

		public void setDuration(Integer duration) {
			this.duration = duration;
		}

		public BigDecimal getDiscount() {
			return discount;
		}

		public void setDiscount(BigDecimal discount) {
			this.discount = discount;
		}
	    
	    
}
