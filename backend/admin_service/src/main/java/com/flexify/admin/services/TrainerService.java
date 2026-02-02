package com.flexify.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.TrainerDetailsDTO;
import com.flexify.admin.dto.TrainerSpecializationDTO;
import com.flexify.admin.entities.Trainer;
import com.flexify.admin.entities.TrainerSpecialization;
import com.flexify.admin.entities.Training;
import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.repositries.TrainerRepository;
import com.flexify.admin.repositries.TrainerSpecializationRepository;
import com.flexify.admin.repositries.TrainingRepository;
import com.flexify.admin.repositries.UserRepository;

@Service
public class TrainerService {
	@Autowired
    private UserRepository userRepo;

    @Autowired
    private TrainerRepository trainerRepo;

    @Autowired
    private TrainerSpecializationRepository specializationRepo;

    @Autowired
    private TrainingRepository trainingRepo;

    /* ================= TRAINER LIST ================= */
    public List<TrainerDetailsDTO> getAllTrainers() {

        List<Trainer> trainerList = trainerRepo.findAll();
        List<TrainerDetailsDTO> response = new ArrayList<>();

        for (Trainer t : trainerList) {

            // 1️⃣ Get user info using uid from trainers table
            UserEntity u = userRepo.findById(t.getUid()).orElse(null);
            if (u == null) continue;

            TrainerDetailsDTO dto = new TrainerDetailsDTO();

            dto.setTid(t.getTid());
            dto.setUid(u.getUid());
            dto.setUname(u.getUname());
            dto.setFname(u.getFname());
            dto.setLname(u.getLname());
            dto.setEmail(u.getEmail());
            dto.setContact(u.getContact());
            dto.setGender(u.getGender());
            dto.setExperience(t.getExperience());
            dto.setSalary(t.getSalary());

            // 2️⃣ Get trainer_specialization using tid
            List<TrainerSpecialization> specs =
                    specializationRepo.findByTid(t.getTid());

            List<TrainerSpecializationDTO> specDTOs = new ArrayList<>();

            for (TrainerSpecialization s : specs) {

                // 3️⃣ Get training name using tr_id
                Training tr = trainingRepo.findById(s.getTrId()).orElse(null);

                TrainerSpecializationDTO sdto = new TrainerSpecializationDTO();
                sdto.setTrId(s.getTrId());
                sdto.setTrainingName(tr != null ? tr.getTrName() : "NA");
                sdto.setDescription(s.getDescription());

                specDTOs.add(sdto);
            }

            dto.setSpecializations(specDTOs);

            response.add(dto);
        }

        return response;
    }

    /* ================= TRAINER DETAILS ================= */
    
    public TrainerDetailsDTO getTrainerDetails(Integer uid) {

        // 1️⃣ Fetch User
        UserEntity u = userRepo.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found for uid: " + uid));

        // 2️⃣ Fetch Trainer
        Trainer t = trainerRepo.findByUid(uid);
        if (t == null) {
            throw new RuntimeException("Trainer not found for uid: " + uid);
        }

        // 3️⃣ Prepare DTO
        TrainerDetailsDTO dto = new TrainerDetailsDTO();
        dto.setTid(t.getTid());
        dto.setUname(u.getUname());
        dto.setFname(u.getFname());
        dto.setLname(u.getLname());
        dto.setEmail(u.getEmail());
        dto.setContact(u.getContact());
        dto.setGender(u.getGender());
        dto.setExperience(t.getExperience());
        dto.setSalary(t.getSalary());

        // 4️⃣ Fetch Specializations
        List<TrainerSpecialization> specs =
                specializationRepo.findByTid(t.getTid());

        List<TrainerSpecializationDTO> specDTOs = new ArrayList<>();

        for (TrainerSpecialization s : specs) {

            Training tr = trainingRepo.findById(s.getTrId())
                    .orElse(null);

            TrainerSpecializationDTO sdto = new TrainerSpecializationDTO();
            sdto.setTrId(s.getTrId());
            sdto.setTrainingName(tr != null ? tr.getTrName() : "NA");
            sdto.setDescription(s.getDescription());

            specDTOs.add(sdto);
        }

        dto.setSpecializations(specDTOs);

        return dto;
    }


	    
}
