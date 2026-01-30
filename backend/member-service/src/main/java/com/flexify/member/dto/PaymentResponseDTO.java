package com.flexify.member.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponseDTO {
	

	    private Integer paymentId;
	    private Integer memberId;
	    private BigDecimal amount;
	    private LocalDateTime paymentDate;
	    private String paymentMethod;
	    private String transactionId;

	    private String membershipStatus;
	    private String planName;

	    // getters & setters
	}


