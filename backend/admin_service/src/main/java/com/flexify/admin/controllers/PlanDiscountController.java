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

import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.services.PlanDiscountService;

@RestController
@RequestMapping("/flexify/admin/discount")
public class PlanDiscountController {
	
	@Autowired
	private PlanDiscountService service;
	
	 // CREATE
    @PostMapping("/addDiscount")
    public PlanDiscount add(@RequestBody PlanDiscount d) {
        return service.add(d);
    }

    // READ ALL
    @GetMapping("/getAllDiscount")
    public List<PlanDiscount> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/getById/{id}")
    public PlanDiscount getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public PlanDiscount update(
            @PathVariable Integer id,
            @RequestBody PlanDiscount d) {
        return service.update(id, d);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Plan discount deleted successfully";
    }
}
