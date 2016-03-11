package modele;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class EvenementSansEquipe extends Evenement{
    @ManyToMany
    private List<Adherent> participants;
}
