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

import com.flexify.admin.entities.MemberMembership;
import com.flexify.admin.services.MemberMembershipService;

@RestController
@RequestMapping("/admin")
public class MemberMembershipController {

		@Autowired
	 	private  MemberMembershipService service;

	    public MemberMembershipController(MemberMembershipService service) {
	        this.service = service;
	    }

	    // CREATE
	    @PostMapping("/memberships/addMembership")
	    public MemberMembership add(@RequestBody MemberMembership m) {
	        return service.addMembership(m);
	    }

	    // READ ALL
	    @GetMapping("/memberships/getAll")
	    public List<MemberMembership> getAll() {
	        return service.getAllMemberships();
	    }

	    // READ BY ID
	    @GetMapping("/memberships/getById/{id}")
	    public MemberMembership getById(@PathVariable Integer id) {
	        return service.getMembershipById(id);
	    }

	    // UPDATE
	    @PutMapping("/memberships/update/{id}")
	    public MemberMembership update(
	            @PathVariable Integer id,
	            @RequestBody MemberMembership m) {
	        return service.updateMembership(id, m);
	    }

	    // DELETE
	    @DeleteMapping("/memberships/delete/{id}")
	    public String delete(@PathVariable Integer id) {
	    	service.deleteMembership(id);
	    	return "Membership deleted successfully";
	    }
}
