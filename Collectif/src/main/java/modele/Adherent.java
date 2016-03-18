package modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Adherent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique=true)
    private String adresse;
    private String mail;
    private Double longitude;
    private Double latitude;
    private String mdp;
    
    @OneToMany
    private List<Demande> demandes;
    
    @ManyToMany
    private List <Evenement> evenements;

    public Adherent() {
    }

    public Adherent(String nom, String prenom, String adresse, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.mdp = mdp;
        
        demandes = new ArrayList<>();
        evenements = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
    
    public String getMdp () {
        return mdp;
    }
    
    public List<Demande> getDemandes() {
        return demandes;
    }
    
    public List <Evenement> getEvenements() {
        return evenements;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCoordonnees(LatLng latLng) {
        this.longitude = latLng.lng;
        this.latitude = latLng.lat;
    }
    
    public void setMdp (String mdp) {
        this.mdp = mdp;
    }
    
    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }
    
    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @Override
    public String toString() {
        String output = "Adherent{" + "id=" + id + ", nom=" + nom + ", prenom=" +
                prenom + ", mail=" + mail + ", adresse=" + adresse +
                ", longitude=" + longitude + ", latitude=" + latitude + '}' +
                "\r\nDemandes de l'adhérent :\r\n";
        for(Demande demande : demandes) {
            output += demande.toString() + "\r\n";
        }
        output += "Evenements de l'adhérent :\r\n";
        for(Evenement evenement : evenements) {
            output += evenement.toString() + "\r\n";
        }
        return output;
    }
}
