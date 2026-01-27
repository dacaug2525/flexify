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

import com.flexify.admin.dto.PlanDTO;
import com.flexify.admin.entities.Plan;
import com.flexify.admin.services.PlanService;

@RestController
@RequestMapping("/flexify/admin/plans")
public class PlanController {
	@Autowired
	private PlanService service;

	@PostMapping("/addPlan")
    public Plan add(@RequestBody PlanDTO dto) {
        return service.addPlan(dto);
    }

    @GetMapping("/getAllPlan")
    public List<Plan> getAll() {
        return service.getAllPlans();
    }

    @GetMapping("/getById/{id}")
    public Plan getPlan(@PathVariable Integer id) {
	    return service.getPlan(id);
    }
    
    
    @PutMapping("/update/{id}")
    public Plan update(@PathVariable Integer id, @RequestBody PlanDTO dto) {
        return service.updatePlan(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.deletePlan(id);
    }
}
