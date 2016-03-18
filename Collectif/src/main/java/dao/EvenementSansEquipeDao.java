package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.EvenementSansEquipe;

public class EvenementSansEquipeDao {
    
    public void create(EvenementSansEquipe evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(evenement);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public EvenementSansEquipe update(EvenementSansEquipe evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            evenement = em.merge(evenement);
        }
        catch(Exception e){
            throw e;
        }
        return evenement;
    }
    
    public EvenementSansEquipe findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        EvenementSansEquipe evenement = null;
        try {
            evenement = em.find(EvenementSansEquipe.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return evenement;
    }
    
    public List<EvenementSansEquipe> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<EvenementSansEquipe> evenements = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a");
            evenements = (List<EvenementSansEquipe>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenements;
    }
}

