package com.aeb;

import com.aeb.modules.professional.domain.Professional;
import com.aeb.modules.professional.infrastructure.MySQLProfessionalRepository;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/betplaydb";
        String username = "root";
        String password = "kike";

        MySQLProfessionalRepository ProRepo = new MySQLProfessionalRepository(url, username, password);
        Professional pro1 = new Professional(1005333201, 2, "Andres", "Bustamante", "siks");
        ProRepo.save(pro1);
        System.out.println(ProRepo.getAll());
    }
}   