package com.aeb;

import com.aeb.modules.Professional.domain.Professional;
import com.aeb.modules.Professional.infrastructure.MySQLProfessionalRepository;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/betplay";
        String username = "root";
        String password = "kike";

        MySQLProfessionalRepository ProRepo = new MySQLProfessionalRepository(url, username, password);
       /*  Professional pro1 = new Professional(2, "Andres", "Bustamante");
        ProRepo.save(pro1); */
        System.out.println(ProRepo.getAll());
    }
}