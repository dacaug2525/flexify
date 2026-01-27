package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.entities.Feedback;
import com.flexify.admin.services.FeedbackService;

@RestController
@RequestMapping("/flexify/admin/feedbacks")
public class FeedbackController {
	@Autowired
	private FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping("/addFeedback")
    public Feedback add(@RequestBody Feedback f) {
        return service.addFeedback(f);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<Feedback> getAll() {
        return service.getAllFeedbacks();
    }

    // READ BY ID
    @GetMapping("/getById/{id}")
    public Feedback getById(@PathVariable Integer id) {
        return service.getFeedbackById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Feedback update(
            @PathVariable Integer id,
            @RequestBody Feedback f) {
        return service.updateFeedback(id, f);
    }

    // DELETE
    @DeleteMapping("/update/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteFeedback(id);
        return "Feedback deleted successfully";
    }
}
