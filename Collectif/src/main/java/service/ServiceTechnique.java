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

    static public List<Adherent> listeAdherent() {
        List<Adherent> listAdherent = null;

        AdherentDao adherentDao = new AdherentDao();

        JpaUtil.creerEntityManager();

        JpaUtil.ouvrirTransaction();

        try {
            listAdherent = adherentDao.findAll();
        } catch (Throwable ex) {
            Logger.getLogger(ServiceTechnique.class.getName()).log(Level.SEVERE, null, ex);
        }

        JpaUtil.validerTransaction();

        JpaUtil.fermerEntityManager();

        return listAdherent;
    }

    static public void mailAdherentInscription(Adherent pAdherent, boolean inscriptionReussi) {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : " + pAdherent.getMail());
        if (inscriptionReussi) {
            System.out.println("Sujet : Bienvenue chez Collect'IF");
            System.out.println("Corps :");
            System.out.println("Bonjour " + pAdherent.getPrenom() + ",");
            System.out.println("Nous vous confirmons votre adhésion à l'association COLLECT'IF. Votre numéro d'adhérent est : " + pAdherent.getId().toString());
        } else {
            System.out.println("Sujet : Problème d'inscription chez Collect'IF");
            System.out.println("Corps :");
            System.out.println("Bonjour " + pAdherent.getPrenom() + ",");
            System.out.println("Votre adhésion à l'association COLLECT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
        }
    }

    static public void mailResponsableInscription(Adherent pAdherent, boolean inscriptionReussi) {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : admin@collectif.org");
        if (inscriptionReussi) {
            System.out.println("Sujet : Inscription chez Collect'IF");
            System.out.println("Corps :");
            System.out.println("Bonjour Admin,");
            System.out.println("Nous vous confirmons l'adhésion à l'association COLLECT'IF de " + pAdherent.getNom() + ". Son numéro d'adhérent est : " + pAdherent.getId().toString());
        } else {
            System.out.println("Sujet : Problème d'inscription chez Collect'IF");
            System.out.println("Corps :");
            System.out.println("Bonjour Admin,");
            System.out.println("Une adhésion a malencontreusement échoué.");
        }
    }
}
