package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.flexify.admin.entities.MemberMembership;
import com.flexify.admin.repositries.MemberMembershipRepository;


@Service
public class MemberMembershipService {
	
	@Autowired
	private MemberMembershipRepository repository;

    public MemberMembershipService(MemberMembershipRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public MemberMembership addMembership(MemberMembership m) {
        return repository.save(m);
    }

    // get ALL
    public List<MemberMembership> getAllMemberships() {
        return repository.findAll();
    }

    // get BY ID
    public MemberMembership getMembershipById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    // UPDATE
    public MemberMembership updateMembership(Integer id, MemberMembership m) {
        MemberMembership existing = getMembershipById(id);

        existing.setMemberId(m.getMemberId());
        existing.setPlanId(m.getPlanId());
        existing.setStartDate(m.getStartDate());
        existing.setEndDate(m.getEndDate());
        existing.setStatus(m.getStatus());

        return repository.save(existing);
    }
    
    // DELETE
    public void deleteMembership(Integer id) {
        repository.deleteById(id);
    }
    
    /*
	@Autowired
    private MemberMembershipRepository memberMembershipRepo;

    @Autowired
    private PlanTrainingRepository planTrainingRepo;

    @Autowired
    private TrainerSpecializationRepository trainerSpecRepo;

    @Autowired
    private TrainerRepository trainersRepo;

    public List<MemberTrainingDTO> getMembersWithTrainings(int planId) {
        List<MemberTrainingDTO> result = new ArrayList<>();

        // Step 1: Get all members for this plan
        List<MemberMembership> memberships = memberMembershipRepo.findByPlanId(planId);

        // Step 2: Get all trainings for this plan
        List<PlanTraining> planTrainings = planTrainingRepo.existsByPlanId(planId);

        // Step 3: Loop through members and trainings
        for (MemberMembership membership : memberships) {
            String memberName = membership.getMember().getFname() + " " + membership.getMember().getLname();

            for (PlanTraining pt : planTrainings) {
                int trainingId = pt.getTrId().tr// Training ID

                // Step 4: Get all trainers for this training
                List<TrainerSpecialization> specs = trainerSpecRepo.findByTrId(trainingId);

                for (TrainerSpecialization spec : specs) {
                    Trainer trainer = spec.getTid(); // Trainer entity
                    String trainerName = trainer.getUid().getUname(); // Assuming trainer linked to Users table
                    String specialization = spec.getDescription();

                    // Add to result
                    result.add(new MemberTrainingDTO(memberName, trainerName, specialization, trainingId));
                }
            }
        }

        return result;
    }
    */
}
