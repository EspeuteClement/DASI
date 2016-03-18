package modele;

import java.util.List;
import javax.persistence.ManyToMany;

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
}
