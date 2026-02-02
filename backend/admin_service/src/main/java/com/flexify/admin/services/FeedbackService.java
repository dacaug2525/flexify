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

	    public List<Feedback> getAllFeedback() {
	        return repository.findAll();
	    }
}
