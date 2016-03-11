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
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return nouvelAdherent;
    }
    
    static public Adherent connexionAdherent(String mail, String mdp)
    {
        List<Adherent> listeAdherent = ServiceTechnique.listeAdherent();
        
        for (int i = 0; i<listeAdherent.size(); i++)
        {
            Adherent adherent;
            adherent = listeAdherent.get(i);
            
            if(adherent.getMail()==mail && adherent.getMdp()==mdp)
            {
                return adherent;
            }
        }
        
        return null;
    }
    
    

}
