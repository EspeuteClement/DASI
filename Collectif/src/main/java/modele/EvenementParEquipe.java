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
}
