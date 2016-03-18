package modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Modèle pour l'objet métier Activite.
 * @author eyjarson
 */
@Entity
public class Activite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String denomination;
    private Boolean parEquipe;
    private Integer nbParticipants;
    
    /**
     * Constructeur vide (obligatoire).
     */
    public Activite() {
    }
    
    /**
     * Constructeur par défaut.
     * @param denomination Le nom de l'activité.
     * @param parEquipe True si l'activité se fait par équipe, false sinon.
     * @param nbParticipants Nombre de participants exact requis pour l'activité.
     */
    public Activite(String denomination, Boolean parEquipe, Integer nbParticipants) {
        this.denomination = denomination;
        this.parEquipe = parEquipe;
        this.nbParticipants = nbParticipants;
    }
    
    /**
     * Getter pour l'ID.
     * @return L'ID de l'activité.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Getter pour le nom de l'activité.
     * @return Le nom de l'activité.
     */
    public String getDenomination() {
        return denomination;
    }
    
    /**
     * Getter pour le type d'activité (avec ou sans équipe).
     * @return True si l'activité se fait par équipe, false sinon.
     */
    public Boolean isParEquipe() {
        return parEquipe;
    }

    public Integer getNbParticipants() {
        return nbParticipants;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setParEquipe(Boolean parEquipe) {
        this.parEquipe = parEquipe;
    }

    public void setNbParticipants(Integer nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", denomination=" + denomination + ", parEquipe=" + parEquipe + ", nbParticipants=" + nbParticipants + '}';
    }
       
}
