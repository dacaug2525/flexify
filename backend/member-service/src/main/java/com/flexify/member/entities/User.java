package com.flexify.member.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String uname;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String contact;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        Male, Female, other
    }
    // getters & setters
}


