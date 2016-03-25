package presentation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modele.Activite;
import modele.Demande;
import modele.Evenement;
import modele.EvenementParEquipe;
import modele.EvenementSansEquipe;
import service.ServiceMetier;
import util.Saisie;

public class Main {

    public static void main(String[] args) {
        
        while (true) {
            System.out.println("COLLECT'IF");
            System.out.println("1 - Lister les activités");
            System.out.println("2 - Lister l'historique des demandes d'un utilisateur");
            System.out.println("3 - Lister les évènements");
            System.out.println("4 - Créer un adhérent");
            System.out.println("5 - Créer une demande");
            System.out.println("6 - Quitter");

            List<Integer> menuChoix = new ArrayList<Integer>();
            menuChoix.add(1);
            menuChoix.add(2);
            menuChoix.add(3);
            menuChoix.add(4);
            menuChoix.add(5);
            menuChoix.add(6);

            int choix = Saisie.lireInteger("Choisissez une option", menuChoix);

            switch (choix) {
                case 1:
                    List<Activite> activites = ServiceMetier.recupererActivites();
                    for(Activite activite : activites)
                    {
                        System.out.println(activite.toString());
                    }
                    break;
                case 2:
                    int idAdherent = Saisie.lireInteger("Entrez l'id de l'utilisateur");
                    List<Demande> demandes = ServiceMetier.recupererAdherentDemandes(idAdherent);
                    for(Demande demande : demandes)
                    {
                        System.out.println(demande.toString());
                    }
                    break;
                case 3:
                    List<Evenement> evenements = ServiceMetier.recupererEvenement();
                    for(Evenement evenement : evenements)
                    {
                        if(evenement instanceof EvenementParEquipe)
                        {
                            EvenementParEquipe evenementAE = (EvenementParEquipe) evenement;
                            System.out.println(evenementAE.toString());
                        }
                        else
                        {
                            EvenementSansEquipe evenementSE = (EvenementSansEquipe) evenement;
                            System.out.println(evenementSE.toString());
                        }
                    }
                    break;
                case 4:
                    String nom = Saisie.lireChaine("Saisissez un nom.");
                    String prenom = Saisie.lireChaine("Saisissez un prénom");
                    ServiceMetier.inscrireAdherent(nom, prenom, adresse, mail, mdp);
                    
                    break;
                default:
                    break;

            }

            if (choix == 6) {
                break;
            }
        }
    }
}
