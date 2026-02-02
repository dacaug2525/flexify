package com.flexify.admin.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer_specialization")
public class TrainerSpecialization {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_Id")
    private Integer trainingId;

    @Column(name = "tid", nullable = false)
    private Integer tid;

    @Column(name = "tr_id", nullable = false)
    private Integer trId;

    @Column(name = "description", length = 255)
    private String description;

    // ===== getters & setters =====

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }}
