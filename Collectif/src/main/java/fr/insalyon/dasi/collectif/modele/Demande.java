package fr.insalyon.dasi.collectif.modele;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String denomination;
    private Date dateDemande;
    private Integer nbDemandes;

    public Demande() {
    }

    public Demande(String denomination, Date date, Integer nbDemandes) {
        this.denomination = denomination;
        this.dateDemande = date;
        this.nbDemandes = nbDemandes;
    }

    public Integer getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }
    
    public Date getDate() {
        return dateDemande;
    }

    public Integer getNbDemandes() {
        return nbDemandes;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    
    public void setDate(Date date) {
        this.dateDemande = date;
    }

    public void setNbDemandes(Integer nbDemandes) {
        this.nbDemandes = nbDemandes;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", denomination=" + denomination + ", nbDemandes=" + nbDemandes + '}';
    }
       
}
