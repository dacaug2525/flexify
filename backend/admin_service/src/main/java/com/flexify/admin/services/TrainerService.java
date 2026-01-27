package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.TrainerDTO;
import com.flexify.admin.entities.Trainer;
import com.flexify.admin.exception.ResourceNotFoundException;
import com.flexify.admin.repositries.TrainerRepository;

@Service
public class TrainerService {
	    @Autowired
	    private TrainerRepository trainerRepository;

	    // CREATE
	    public Trainer addTrainer(TrainerDTO dto) {
	        Trainer trainer = new Trainer();
	        mapDtoToEntity(dto, trainer);
	        return trainerRepository.save(trainer);
	    }

	    // READ BY ID
	    public Trainer getTrainerById(Integer id) {
	        return trainerRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
	    }

	    // READ ALL
	    public List<Trainer> getAllTrainers() {
	        return trainerRepository.findAll();
	    }

	    // UPDATE
	    public Trainer updateTrainer(Integer id, TrainerDTO dto) {
	        Trainer trainer = getTrainerById(id);
	        mapDtoToEntity(dto, trainer);
	        return trainerRepository.save(trainer);
	    }

	    // DELETE
	    public void deleteTrainer(Integer id) {
	        Trainer trainer = getTrainerById(id);
	        trainerRepository.delete(trainer);
	    }

	    // helper
	    private void mapDtoToEntity(TrainerDTO dto, Trainer trainer) {
	        trainer.setExperience(dto.getExperience());
	        trainer.setSalary(dto.getSalary());
	        trainer.setUid(dto.getUid());
	    }
}
