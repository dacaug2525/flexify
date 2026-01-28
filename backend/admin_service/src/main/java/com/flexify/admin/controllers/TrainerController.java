package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.TrainerDTO;
import com.flexify.admin.entities.Trainer;
import com.flexify.admin.services.TrainerService;

@RestController
@RequestMapping("/flexify/admin/trainers")
public class TrainerController {
	    @Autowired
	    private TrainerService trainerService;

	    @PostMapping("/add")
	    public ResponseEntity<Trainer> addTrainer(@RequestBody TrainerDTO dto) {
	        return ResponseEntity.ok(trainerService.addTrainer(dto));
	    }

	    @GetMapping("/getById/{id}")
	    public ResponseEntity<Trainer> getTrainer(@PathVariable Integer id) {
	        return ResponseEntity.ok(trainerService.getTrainerById(id));
	    }

	    @GetMapping("/getAll")
	    public ResponseEntity<List<Trainer>> getAllTrainers() {
	        return ResponseEntity.ok(trainerService.getAllTrainers());
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<Trainer> updateTrainer(
	            @PathVariable Integer id,
	            @RequestBody TrainerDTO dto) {
	        return ResponseEntity.ok(trainerService.updateTrainer(id, dto));
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteTrainer(@PathVariable Integer id) {
	        trainerService.deleteTrainer(id);
	        return ResponseEntity.ok("Trainer deleted successfully");
	    }
}
