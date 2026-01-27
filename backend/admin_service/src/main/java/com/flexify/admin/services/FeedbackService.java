package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.entities.Feedback;
import com.flexify.admin.repositries.FeedbackRepository;

@Service
public class FeedbackService {
	    @Autowired
	    private  FeedbackRepository repository;

	    public FeedbackService(FeedbackRepository repository) {
	        this.repository = repository;
	    }

	    // CREATE
	    public Feedback addFeedback(Feedback f) {
	        return repository.save(f);
	    }

	    // READ ALL
	    public List<Feedback> getAllFeedbacks() {
	        return repository.findAll();
	    }

	    // READ BY ID
	    public Feedback getFeedbackById(Integer id) {
	        return repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Feedback not found"));
	    }

	    // UPDATE
	    public Feedback updateFeedback(Integer id, Feedback f) {
	        Feedback existing = getFeedbackById(id);

	        existing.setMid(f.getMid());
	        existing.setTid(f.getTid());
	        existing.setComment(f.getComment());
	        existing.setDate(f.getDate());
	        existing.setRating(f.getRating());

	        return repository.save(existing);
	    }

	    // DELETE
	    public void deleteFeedback(Integer id) {
	        repository.deleteById(id);
	    }
}
