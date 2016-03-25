package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Equipe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
        
    @ManyToMany
    private List<Adherent> participants;
    
    public Equipe() {
        participants = new ArrayList<>();
    }
    
    public Integer getId() {
        return id;
    }
    
    public List<Adherent> getParticipants() {
        return participants;
    }
    
    public void setParticipants(List<Adherent> adherents) {
        this.participants = adherents;
    }
}
