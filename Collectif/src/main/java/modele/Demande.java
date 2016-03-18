package modele;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date dateDemande;
    private Date dateEvenement;
    
    @ManyToOne
    private Activite activite;
    
    @ManyToOne
    private Adherent demandeur;

    public Demande() {
    }

    public Demande(Date dateDemande, Date dateEvenement, Activite activite, Adherent demandeur) {
        this.dateDemande = dateDemande;
        this.dateEvenement = dateEvenement;
        this.activite = activite;
        this.demandeur = demandeur;
    }

    public Integer getId() {
        return id;
    }
    
    public Date getDateDemande() {
        return dateDemande;
    }
    
    public Date getDateEvenement() {
        return dateEvenement;
    }
    
    public Activite getActivite() {
        return activite;
    }
    
    public Adherent getDemandeur() {
        return demandeur;
    }
    
    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }
    
    public void setDateEvenement(Date dateEvenement) {
        this.dateDemande = dateEvenement;
    }
    
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    public void setDemandeur(Adherent demandeur) {
        this.demandeur = demandeur;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", DateDemande=" + dateDemande.toString() + ", DateEvenement="+ dateEvenement.toString() + activite.toString() + " " + demandeur.toString() + '}';
    }
       
}
