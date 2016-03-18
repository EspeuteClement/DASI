package presentation;

import service.ServiceMetier;

public class Main {
    public static void main(String[] args) {
        
        ServiceMetier.inscrireAdherent("Capelle", "Victor", "36 rue de la cité 69003 Lyon", "capellev.info@gmail.com", "admin");
        ServiceMetier.inscrireAdherent("Capelle", "Victor", "36 rue de la cité 69003 Lyon", "capellev.info@gmail.com", "admin");
        System.out.println(ServiceMetier.connexionAdherent("capellev.info@gmail.com", "admin").toString());
        
    }
}
