package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.repositries.PlanDiscountRepository;

@Service
public class PlanDiscountService {
		@Autowired
		private PlanDiscountRepository repo;

	   
	    public PlanDiscountService(PlanDiscountRepository repository) {
	        this.repo = repository;
	    }

	    // CREATE
	    public PlanDiscount add(PlanDiscount discount) {
	        return repo.save(discount);
	    }

	    // get ALL
	    public List<PlanDiscount> getAll() {
	        return repo.findAll();
	    }

	    // get BY ID
	    public PlanDiscount getById(Integer id) {
	        return repo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Discount not found"));
	    }

	    // UPDATE
	    public PlanDiscount update(Integer id, PlanDiscount d) {
	        PlanDiscount existing = getById(id);
	        existing.setDuration(d.getDuration());
	        existing.setDiscount(d.getDiscount());
	        return repo.save(existing);
	    }

	    // DELETE
	    public void delete(Integer id) {
	        repo.deleteById(id);
	    }
}
