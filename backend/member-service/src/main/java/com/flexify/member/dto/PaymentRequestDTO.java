package com.flexify.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class PaymentRequestDTO {

	    @NotNull(message = "Member ID is required")
	    private Integer memberId;

	    @NotNull(message = "Plan ID is required")
	    private Integer planId;

	    @NotNull(message = "Amount is required")
	    private BigDecimal amount;

	    @NotNull(message = "Payment date is required")
	    private LocalDateTime paymentDate;

	    @NotNull(message = "Payment method is required")
	    @Size(min = 2, max = 45)
	    private String paymentMethod;

	    // NO transactionId here (backend generates it)

	    // getters & setters
	}


