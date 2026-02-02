package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.PlanDiscountDTO;
import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.repositries.PlanDiscountRepository;

@Service
public class PlanDiscountService {
		@Autowired
		private PlanDiscountRepository repo;

	   
	    public PlanDiscountService(PlanDiscountRepository repository) {
	        this.repo = repository;
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
	    public List<PlanDiscountDTO> getAllDiscounts() {
	        return repo.findAll().stream().map(d -> {
	            PlanDiscountDTO dto = new PlanDiscountDTO();
	            dto.setDisId(d.getDisId());   // <-- now it exists
	            dto.setDiscount(d.getDiscount());
	            dto.setDuration(d.getDuration());
	            return dto;
	        }).toList();
	    }
}
