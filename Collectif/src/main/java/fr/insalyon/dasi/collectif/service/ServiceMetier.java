package fr.insalyon.dasi.collectif.service;

import fr.insalyon.dasi.collectif.modele.Adherent;
import fr.insalyon.dasi.collectif.dao.AdherentDao;
import fr.insalyon.dasi.collectif.dao.JpaUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceMetier {

    static public Adherent inscrireAdherent(String nom, String prenom, String adresse, String mail, String mdp) {
        AdherentDao adherentDao = new AdherentDao();
        
        Adherent nouvelAdherent = new Adherent(nom,prenom,adresse,mail,mdp,false);
        
        JpaUtil.creerEntityManager();
        
        JpaUtil.ouvrirTransaction();
        
        try {
            adherentDao.create(nouvelAdherent);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return nouvelAdherent;
    }

}
