package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public abstract class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date dateEvenement;
    
    @ManyToOne
    private Lieu lieu;
    
    @ManyToOne
    private Activite activite;
    
    @OneToMany
    private List<Demande> demandes;

    public Evenement() {
    }

    public Evenement(Date date, Lieu lieu, Activite activite, List<Demande> demandes) {
        this.dateEvenement = date;
        this.lieu = new Lieu();
        this.activite = activite;
        this.demandes = demandes;
    }

    public Integer getId() {
        return id;
    }
    
    public Date getDate() {
        return dateEvenement;
    }
    
    public void setDate(Date date) {
        this.dateEvenement = date;
    }

    @Override
    public String toString() {
        String output = "Evenement{" + "id=" + id + ", Date=" +
                dateEvenement.toString() + " " + lieu.toString() + " " +
                activite.toString() + "\r\nDemandes de l'évènement :\r\n" + '}';
        for(Demande demande : demandes) {
            output += demande.toString() + "\r\n";
        }
        return output;
    }
}
