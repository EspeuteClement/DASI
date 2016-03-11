package fr.insalyon.dasi.collectif.presentation;

import fr.insalyon.dasi.collectif.service.ServiceMetier;

public class Main {
    public static void main(String[] args) {
        
        ServiceMetier serviceMetier = new ServiceMetier();
        
        serviceMetier.inscrireAdherent("Capelle", "Victor", "36 Rue", "capellev.info@gmail.com", "admin");
        
    }
}
