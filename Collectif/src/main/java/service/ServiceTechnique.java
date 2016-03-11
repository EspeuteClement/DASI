package service;

import com.google.maps.model.LatLng;
import dao.AdherentDao;
import dao.JpaUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Adherent;
import util.GeoTest;

public class ServiceTechnique {

    static public LatLng recuperationGeoloc(String adresse) {
        
        return GeoTest.getLatLng(adresse);
    }
    
    static public List<Adherent> listeAdherent()
    {
        List<Adherent> listAdherent = null;
        
        AdherentDao adherentDao = new AdherentDao();
        
        JpaUtil.creerEntityManager();
        
        JpaUtil.ouvrirTransaction();
        
        try {
            listAdherent=adherentDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceTechnique.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return listAdherent;
    }
}
