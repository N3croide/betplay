package com.aeb;

import com.aeb.modules.professional.adapters.in.ProfessionalConsoleAdapter;
import com.aeb.modules.professional.adapters.out.MySQLProfessionalRepository;
import com.aeb.modules.professional.application.ProfessionalService;
import com.aeb.modules.professional.domain.Professional;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/betplaydb";
        String username = "root";
        String password = "kike";

        MySQLProfessionalRepository out = new MySQLProfessionalRepository(url, username, password);
        ProfessionalService serv = new ProfessionalService(out);
        ProfessionalConsoleAdapter in = new ProfessionalConsoleAdapter(serv);

        in.start();
        
        
    }
}   