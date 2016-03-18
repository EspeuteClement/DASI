package modele;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class EvenementSansEquipe extends Evenement{
    @ManyToMany
    private List<Adherent> participants;
    
    public List<Adherent> getParticipants() {
        return participants;
    }
    
    public void setParticipants(List<Adherent> participants) {
        this.participants = participants;
    }
    
        @Override
    public String toString() {
        String output = super.toString();
        output += "Participants :\r\n";
        for(Adherent participant : participants) {
            output += participant.toString() + "\r\n";
        }
        return output;
    }
}
