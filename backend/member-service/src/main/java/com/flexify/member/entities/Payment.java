package com.flexify.member.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "payment")
public class Payment {
	

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer paymentId;

	    // FK → members(mid)
	    @Column(nullable = false)
	    private Integer mid;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal amount;

	    @Column(nullable = false)
	    private LocalDateTime paymentDate;

	    @Column(nullable = false, length = 45)
	    private String paymentMethod;

	    // AUTO-GENERATED, UNIQUE, 10 CHAR
	    @Column(nullable = false, unique = true, length = 10)
	    private String transactionId;

	    // getters & setters
	

}
