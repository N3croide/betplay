package com.aeb.modules.professional.application;

import java.util.Optional;

import com.aeb.modules.professional.domain.Professional;
import com.aeb.modules.professional.infrastructure.IProfessional;

import java.util.List;


public class ProfessionalService {
    private final IProfessional professionalRepo;

    public ProfessionalService(IProfessional professionalRepo){
        this.professionalRepo = professionalRepo;
    }

    public void createProfessional(Professional professional){
        professionalRepo.save(professional);
    }
}
