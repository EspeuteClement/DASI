package modele;

import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EvenementParEquipe extends Evenement {
    @OneToOne
    private Equipe equipeA;
    
    @OneToOne
    private Equipe equipeB;
    
    public EvenementParEquipe() {
    }
    
    public EvenementParEquipe(Date date, Activite activite, List<Demande> demandes) {
        super(date, activite, demandes);
        equipeA = new Equipe();
        equipeB = new Equipe();
    }
    
    public Equipe getEquipeA() {
        return equipeA;
    }
    
    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }
    
    public Equipe getEquipeB() {
        return equipeB;
    }
    
    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }
    
    @Override
    public String toString() {
        String output = super.toString();
        output += "Participants de l'équipe A :\r\n";
        for(Adherent participant : equipeA.getParticipants()) {
            output += participant.toString() + "\r\n";
        }
        output += "Participants de l'équipe B :\r\n";
        for(Adherent participant : equipeB.getParticipants()) {
            output += participant.toString() + "\r\n";
        }
        return output;
    }
}
