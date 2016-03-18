package presentation;

import service.ServiceMetier;

public class Main {
    public static void main(String[] args) {
        ServiceMetier.inscrireAdherent("Albert", "Einstein", "18 Avenue Albert Einstein VILLEURBANNE", "albert.einstein@insa-lyon.fr", "aeinstein");
        ServiceMetier.inscrireAdherent("Benoit", "Renault", "20 Avenue Albert Einstein VILLEURBANNE", "benoit.renault@insa-lyon.fr", "brenault");
        ServiceMetier.inscrireAdherent("Capelle", "Victor", "36 rue de la cité 69003 Lyon", "capellev.info@gmail.com", "admin");
        System.out.println(ServiceMetier.connexionAdherent("capellev.info@gmail.com", "admin").toString());
    }
}
