package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flexify.admin.entities.Feedback;
import com.flexify.admin.services.FeedbackService;

@RestController
@RequestMapping("/admin")
public class FeedbackController {
	@Autowired
	private FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @GetMapping("/feedbacks/allfeedbacks")
    public List<Feedback> getAllFeedback() {
        return service.getAllFeedback();
    }
}
