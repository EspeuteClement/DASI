package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
    private String denomination;
    private Date dateEvenement;
    
    @ManyToOne
    private Lieu lieu;
    
    @ManyToOne
    private Activite activite;
    
    @OneToMany
    private List<Demande> demandes;

    public Evenement() {
    }

    public Evenement(String denomination, Date date, Lieu lieu, Activite activite) {
        this.denomination = denomination;
        this.dateEvenement = date;
        this.lieu = lieu;
        this.activite = activite;
        this.demandes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }
    
    public Date getDate() {
        return dateEvenement;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    
    public void setDate(Date date) {
        this.dateEvenement = date;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", denomination=" + denomination + ", Date=" + dateEvenement.toString() + lieu.toString() + '}';
    }
}
