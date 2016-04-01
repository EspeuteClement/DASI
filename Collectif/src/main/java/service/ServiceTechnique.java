package service;

import com.google.maps.model.LatLng;
import modele.Adherent;
import modele.Evenement;
import modele.EvenementParEquipe;
import modele.EvenementSansEquipe;
import modele.Lieu;
import util.GeoTest;

public class ServiceTechnique {

    /**
     * Récupère la géolocalisation d'une adresse.
     *
     * @param adresse Adresse à géolocaliser.
     * @return Retourne un objet LatLng contenant la latitude et la longitude de
     * l'adresse.
     */
    static public LatLng recuperationGeoloc(String adresse) {
        return GeoTest.getLatLng(adresse);
    }

    /**
     * Calcule la distance entre l'adresse d'un adhérent et un lieu.
     *
     * @param pAdherent Adhérent.
     * @param pLieu Lieu.
     * @return Retourne la distance entre l'adresse de l'adhérent et du lieu.
     */
    static public double Distance(Adherent pAdherent, Lieu pLieu) {
        double distance;

        distance = GeoTest.getFlightDistanceInKm(new LatLng(pAdherent.getLatitude(), pAdherent.getLongitude()), new LatLng(pLieu.getLatitude(), pLieu.getLongitude()));

        return distance;
    }

    /**
     * Envoie un mail à l'adhérent confirmant on infirmant son inscription.
     *
     * @param pAdherent Adhérent destinataire du mail.
     * @param inscriptionReussi True si l'inscription a réussi, false sinon.
     */
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

    /**
     * Envoie un mail au responsable le prévenant d'une inscrption ou d'une
     * tentative d'inscription en cas d'échec.
     *
     * @param pAdherent Adhérent inscrit.
     * @param inscriptionReussi True si l'inscription a réussi, false sinon.
     */
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

    /**
     * Envoi un mail à un adhérent le prévenant qu'un évènement a été créé et
     * localisé.
     *
     * @param pAdherent Destinataire.
     * @param pEvenement Evènement créé.
     */
    static public void mailParticipantEvenement(Adherent pAdherent, Evenement pEvenement) {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : " + pAdherent.getMail());
        System.out.println("Sujet : Nouvel Evènement");
        System.out.println("Corps :");
        System.out.println("Bonjour " + pAdherent.getPrenom() + ",");
        System.out.println();
        System.out.println("Comme vous l'aviez souhaité, COLLECT'IF organise un évènement de " + pEvenement.getActivite().getDenomination() + " le " + pEvenement.getDate().toString() + ".");
        System.out.println("Vous trouverez ci-dessous les détails de cet évènement.");
        System.out.println();
        System.out.println("Associativement vôtre,");
        System.out.println();
        System.out.println("Le Responsable de l'Association");
        System.out.println();
        System.out.println();
        System.out.println("Evènement : " + pEvenement.getActivite().getDenomination());
        System.out.println("Date : " + pEvenement.getDate().toString());
        System.out.println("Lieu : " + pEvenement.getLieu().toMailString());
        System.out.println("(à " + ServiceTechnique.Distance(pAdherent, pEvenement.getLieu()) + " km de chez vous)");
        System.out.println();
        System.out.println("Vous jouerez avec :");
        if (pEvenement instanceof EvenementParEquipe) {
            EvenementParEquipe evenementParEquipe = (EvenementParEquipe) pEvenement;
            if (evenementParEquipe.getEquipeA().getParticipants().contains(pAdherent)) {
                for (Adherent participant : evenementParEquipe.getEquipeA().getParticipants()) {
                    if (!participant.equals(pAdherent)) {
                        System.out.println(participant.getPrenomNom());
                    }

                }

                System.out.println("Contre :");

                for (Adherent participant : evenementParEquipe.getEquipeB().getParticipants()) {
                    System.out.println(participant.getPrenomNom());
                }
            } else {
                for (Adherent participant : evenementParEquipe.getEquipeB().getParticipants()) {
                    if (!participant.equals(pAdherent)) {
                        System.out.println(participant.getPrenomNom());
                    }

                }

                System.out.println("Contre :");

                for (Adherent participant : evenementParEquipe.getEquipeA().getParticipants()) {
                    System.out.println(participant.getPrenomNom());
                }
            }
        } else {
            EvenementSansEquipe evenementSansEquipe = (EvenementSansEquipe) pEvenement;

            for (Adherent participant : evenementSansEquipe.getParticipants()) {
                System.out.println(participant.getPrenomNom());
            }
        }

    }
}
