package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.EvenementParEquipe;

public class EvenementParEquipeDao {
    
    public void create(EvenementParEquipe evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(evenement);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public EvenementParEquipe update(EvenementParEquipe evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            evenement = em.merge(evenement);
        }
        catch(Exception e){
            throw e;
        }
        return evenement;
    }
    
    public EvenementParEquipe findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        EvenementParEquipe evenement = null;
        try {
            evenement = em.find(EvenementParEquipe.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return evenement;
    }
    
    public List<EvenementParEquipe> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<EvenementParEquipe> evenements = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a");
            evenements = (List<EvenementParEquipe>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenements;
    }
}

