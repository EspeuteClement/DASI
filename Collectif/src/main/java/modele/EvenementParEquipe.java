package modele;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class EvenementParEquipe extends Evenement {
    @ManyToMany
    private List<Adherent> equipeA;
    
    @ManyToMany
    private List<Adherent> equipeB;
    
    public List<Adherent> getEquipeA() {
        return equipeA;
    }
    
    public void setEquipeA(List<Adherent> equipeA) {
        this.equipeA = equipeA;
    }
    
    public List<Adherent> getEquipeB() {
        return equipeB;
    }
    
    public void setEquipeB(List<Adherent> equipeB) {
        this.equipeB = equipeB;
    }
    
    @Override
    public String toString() {
        String output = super.toString();
        output += "Participants de l'équipe A :\r\n";
        for(Adherent participant : equipeA) {
            output += participant.toString() + "\r\n";
        }
        output += "Participants de l'équipe B :\r\n";
        for(Adherent participant : equipeB) {
            output += participant.toString() + "\r\n";
        }
        return output;
    }
}
