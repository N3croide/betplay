package com.aeb.modules.professional.adapters.in;

import java.util.Scanner;

import com.aeb.modules.professional.application.ProfessionalService;
import com.aeb.modules.professional.domain.Professional;

public class ProfessionalConsoleAdapter {
    private final ProfessionalService crear;

    public ProfessionalConsoleAdapter(ProfessionalService crear){
        this.crear = crear;
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        Professional pro1 = new Professional(1005333201, 2, "Andres", "Bustamante", "siks");
        System.out.println(pro1);
    }
}
