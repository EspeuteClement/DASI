package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Equipe;

public class EquipeDao {
    
    public void create(Equipe equipe) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(equipe);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Equipe update(Equipe equipe) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            equipe = em.merge(equipe);
        }
        catch(Exception e){
            throw e;
        }
        return equipe;
    }
    
    public Equipe findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Equipe equipe = null;
        try {
            equipe = em.find(Equipe.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return equipe;
    }
    
    public List<Equipe> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Equipe> equipes = null;
        try {
            Query q = em.createQuery("SELECT a FROM Equipe a");
            equipes = (List<Equipe>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return equipes;
    }
}
