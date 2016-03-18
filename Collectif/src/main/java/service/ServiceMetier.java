package service;

import com.google.maps.model.LatLng;
import dao.ActiviteDao;
import modele.Adherent;
import dao.AdherentDao;
import dao.DemandeDao;
import dao.JpaUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import modele.Activite;
import modele.Demande;
import modele.Evenement;

public class ServiceMetier {

    static public boolean inscrireAdherent(String nom, String prenom, String adresse, String mail, String mdp) {
        AdherentDao adherentDao = new AdherentDao();
        Adherent nouvelAdherent = new Adherent(nom, prenom, adresse, mail, mdp, false);
        LatLng geoloc = ServiceTechnique.recuperationGeoloc(adresse);
        boolean succes = true;

        nouvelAdherent.setCoordonnees(geoloc);

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            adherentDao.create(nouvelAdherent);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            succes = false;
        }

        try {
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            succes = false;
        }

        JpaUtil.fermerEntityManager();

        ServiceTechnique.mailAdherentInscription(nouvelAdherent, succes);
        ServiceTechnique.mailResponsableInscription(nouvelAdherent, succes);

        return succes;
    }

    static public Adherent connexionAdherent(String mail, String mdp) {
        List<Adherent> listeAdherent = null;
        AdherentDao adherentDao = new AdherentDao();

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            listeAdherent = adherentDao.findByMail(mail);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        for (int i = 0; i < listeAdherent.size(); i++) {
            Adherent adherent;
            adherent = listeAdherent.get(i);

            if (adherent.getMdp().equals(mdp)) {
                return adherent;
            }
        }

        return null;
    }
    
    static public List<Demande> recupererAdherentDemandes(long idAdherent)
    {
        AdherentDao adherentDao = new AdherentDao();
        Adherent adherent = null;
        List<Demande> adherentDemandes = null;
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            adherent=adherentDao.findById(idAdherent);
            adherentDemandes=adherent.getDemandes();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adherentDemandes;
    }

    static public List<Activite> recupererActivites() {
        ActiviteDao activiteDao = new ActiviteDao();
        List<Activite> activiteList = null;

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            activiteList = activiteDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        return activiteList;
    }

    static public boolean posterDemande(long idAdherent, long idActivite, Date date) {
        AdherentDao adherentDao = new AdherentDao();
        ActiviteDao activiteDao = new ActiviteDao();
        DemandeDao demandeDao = new DemandeDao();
        boolean succes = true;

        Adherent demandeur = null;
        Activite activite = null;

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            demandeur = adherentDao.findById(idAdherent);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            activite = activiteDao.findById(idActivite);
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (demandeur == null || activite == null) {
            succes = false;
        } else {
            Demande nouvelDemande = new Demande(date, Date.valueOf(LocalDate.now()), activite, demandeur);

            try {
                demandeDao.create(nouvelDemande);
                demandeur.getDemandes().add(nouvelDemande);
                adherentDao.update(demandeur);
            } catch (Throwable ex) {
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*if (succes) {
                ServiceMetier.creerEvenement(nouvelDemande);
            }*/
        }

        try {
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            succes = false;
        }
        JpaUtil.fermerEntityManager();

        return succes;
    }
    
    static public void creerEvenement(Demande pDemande)
    {
        int nbDemandes = 0;
        DemandeDao demandeDao = new DemandeDao();
        List<Demande> demandes = null;
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            demandes = demandeDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0 ; i<demandes.size() ; i ++)
        {
            if(demandes.get(i).getActivite().equals(pDemande.getActivite()) && demandes.get(i).getDateEvenement().equals(pDemande.getDateEvenement()))
            {
                nbDemandes++;
            }
        }
        
        if(nbDemandes >= pDemande.getActivite().getNbParticipants())
        {
            //Créer Evenement
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

}
