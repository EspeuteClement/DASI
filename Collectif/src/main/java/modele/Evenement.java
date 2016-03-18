package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String denomination;
    @Temporal(TemporalType.DATE)
    private Date dateEvenement;
    
    @ManyToOne
    private Lieu lieu;
    
    @ManyToOne
    private Activite activite;
    
    @OneToMany
    private List<Demande> demandes;

    public Evenement() {
    }

    public Evenement(String denomination, Date date, Lieu lieu, Activite activite, List<Demande> demandes) {
        this.denomination = denomination;
        this.dateEvenement = date;
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
        return "Activite{" + "id=" + id + ", denomination=" + denomination + ", Date=" + dateEvenement.toString() + '}';
    }
       
}
