package service;

import com.google.maps.model.LatLng;
import modele.Adherent;
import dao.AdherentDao;
import dao.JpaUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceMetier {

    static public Adherent inscrireAdherent(String nom, String prenom, String adresse, String mail, String mdp) {
        AdherentDao adherentDao = new AdherentDao();
        
        Adherent nouvelAdherent = new Adherent(nom,prenom,adresse,mail,mdp,false);
        
        LatLng geoloc = ServiceTechnique.recuperationGeoloc(adresse);
        
        nouvelAdherent.setCoordonnees(geoloc);
        
        JpaUtil.creerEntityManager();
        
        JpaUtil.ouvrirTransaction();
        
        try {
            adherentDao.create(nouvelAdherent);
            ServiceTechnique.mailAdherentInscription(nouvelAdherent, true);
            ServiceTechnique.mailResponsableInscription(nouvelAdherent, true);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            ServiceTechnique.mailAdherentInscription(nouvelAdherent, false);
            ServiceTechnique.mailResponsableInscription(nouvelAdherent, false);
        }
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return nouvelAdherent;
    }
    
    static public Adherent connexionAdherent(String mail, String mdp)
    {
        List<Adherent> listeAdherent = null;
        AdherentDao adherentDao = new AdherentDao();
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            listeAdherent = adherentDao.findByMail(mail);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i<listeAdherent.size(); i++)
        {
            Adherent adherent;
            adherent = listeAdherent.get(i);
            
            if(adherent.getMdp().equals(mdp))
            {
                return adherent;
            }
        }
        
        return null;
    }

}
