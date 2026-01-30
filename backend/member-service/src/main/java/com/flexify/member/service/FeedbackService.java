package com.flexify.member.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.FeedbackRequestDTO;
import com.flexify.member.entities.Feedback;
import com.flexify.member.repository.FeedbackRepository;

@Service
public class FeedbackService {


	    @Autowired
	    private FeedbackRepository repo;

	    public Feedback giveFeedback(FeedbackRequestDTO dto) {
	        Feedback f = new Feedback();
	        f.setMid(dto.getMid());
	        f.setTid(dto.getTid());
	        f.setComment(dto.getComment());
	        f.setRating(dto.getRating());
	        f.setDate(LocalDateTime.now());
	        return repo.save(f);
	    }
	}


