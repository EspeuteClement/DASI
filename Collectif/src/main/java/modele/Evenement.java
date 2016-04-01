package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateEvenement;
    
    @ManyToOne
    private Lieu lieu;
    
    @ManyToOne
    private Activite activite;
    
    @OneToMany
    private List<Demande> demandes;

    public Evenement() {
    }

    public Evenement(Date date, Activite activite, List<Demande> demandes) {
        this.dateEvenement = date;
        this.lieu = null;
        this.activite = activite;
        this.demandes = demandes;
    }

    public Long getId() {
        return id;
    }
    
    public Date getDate() {
        return dateEvenement;
    }
    
    public Lieu getLieu() {
        return lieu;
    }
    
    public Activite getActivite() {
        return activite;
    }
    
    public List<Demande> getDemandes() {
        return demandes;
    }
    
    public void setDate(Date date) {
        this.dateEvenement = date;
    }
    
    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
    
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }

    @Override
    public String toString() {
        String output = "Evenement{" + "id=" + id + ", Date=" +
                dateEvenement.toString() + " " +
                activite.toString() + "\r\nDemandes de l'évènement :\r\n" + '}';
        for(Demande demande : demandes) {
            output += demande.toString() + "\r\n";
        }
        return output;
    }
}
