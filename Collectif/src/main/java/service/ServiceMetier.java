package service;

import com.google.maps.model.LatLng;
import dao.ActiviteDao;
import modele.Adherent;
import dao.AdherentDao;
import dao.DemandeDao;
import dao.EvenementDao;
import dao.JpaUtil;
import dao.LieuDao;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import modele.Activite;
import modele.Demande;
import modele.Evenement;
import modele.EvenementParEquipe;
import modele.EvenementSansEquipe;
import modele.Lieu;

public class ServiceMetier {

    static public boolean inscrireAdherent(String nom, String prenom, String adresse, String mail, String mdp) {
        AdherentDao adherentDao = new AdherentDao();
        Adherent nouvelAdherent = new Adherent(nom, prenom, adresse, mail, mdp);
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

    static public List<Demande> recupererAdherentDemandes(long idAdherent) {
        AdherentDao adherentDao = new AdherentDao();
        Adherent adherent = null;
        List<Demande> adherentDemandes = null;

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            adherent = adherentDao.findById(idAdherent);
            adherentDemandes = adherent.getDemandes();
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

            boolean dejaExistante = false;
            for (int i = 0; i < demandeur.getDemandes().size(); i++) {
                if (demandeur.getDemandes().get(i).getActivite().equals(activite) && demandeur.getDemandes().get(i).getDateEvenement().equals(date)) {
                    dejaExistante = true;
                }
            }

            if (!dejaExistante) {
                Demande nouvelDemande = new Demande(date, Date.valueOf(LocalDate.now()), activite, demandeur);

                try {
                    demandeDao.create(nouvelDemande);
                    demandeur.getDemandes().add(nouvelDemande);
                    adherentDao.update(demandeur);
                } catch (Throwable ex) {
                    Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (succes) {
                    ServiceMetier.creerEvenement(nouvelDemande);
                }
            }
        }

        try {
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            succes = false;
        }
        JpaUtil.fermerEntityManager();

        return succes;
    }

    static public void creerEvenement(Demande pDemande) {
        DemandeDao demandeDao = new DemandeDao();
        List<Demande> demandes = null;
        List<Demande> evenementDemandes = new ArrayList();
        List<Adherent> participants = new ArrayList();

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            demandes = demandeDao.findAll();

            for (int i = 0; i < demandes.size(); i++) {
                if (demandes.get(i).getActivite().equals(pDemande.getActivite()) && demandes.get(i).getDateEvenement().equals(pDemande.getDateEvenement()));
                {
                    evenementDemandes.add(demandes.get(i));
                    participants.add(demandes.get(i).getDemandeur());
                }
            }

            if (evenementDemandes.size() >= pDemande.getActivite().getNbParticipants()) {
                EvenementDao evenementDao = new EvenementDao();

                if (pDemande.getActivite().isParEquipe()) {
                    EvenementParEquipe nouvelEvenement = new EvenementParEquipe(pDemande.getDateEvenement(), pDemande.getActivite(), evenementDemandes);

                    for (int i = 0; i < pDemande.getActivite().getNbParticipants(); i++) {
                        Random rand = new Random();
                        int nombreAleatoire = rand.nextInt(pDemande.getActivite().getNbParticipants() - i);

                        if (i % 2 == 0) {
                            nouvelEvenement.getEquipeA().add(participants.get(nombreAleatoire));
                            participants.remove(nombreAleatoire);
                        } else {
                            nouvelEvenement.getEquipeB().add(participants.get(nombreAleatoire));
                            participants.remove(nombreAleatoire);
                        }
                    }

                    evenementDao.create(nouvelEvenement);
                } else {
                    EvenementSansEquipe nouvelEvenement = new EvenementSansEquipe(pDemande.getDateEvenement(), pDemande.getActivite(), evenementDemandes, participants);
                    evenementDao.create(nouvelEvenement);
                }
            }
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    static public List<Lieu> recupererLieux() {
        LieuDao lieuDao = new LieuDao();
        List<Lieu> lieux = null;

        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            lieux = lieuDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        return lieux;
    }
    
    static public List<Evenement> recupererEvenement()
    {
        EvenementDao evenementDao = new EvenementDao();
        List<Evenement> evenements = null;
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try {
            evenements = evenementDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        return evenements;
    }

}
