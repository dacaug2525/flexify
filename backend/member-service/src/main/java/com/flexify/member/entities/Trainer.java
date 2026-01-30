package com.flexify.member.entities;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer tid;

	    private int experience;
	    private BigDecimal salary;

	    // FK → users.uid
	    @ManyToOne
	    @JoinColumn(name = "uid", nullable = false)
	    private User user;
	}


