package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Adherent;

public class AdherentDao {

    public void create(Adherent adherent) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(adherent);
        } catch (Exception e) {
            throw e;
        }
    }

    public Adherent update(Adherent adherent) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            adherent = em.merge(adherent);
        } catch (Exception e) {
            throw e;
        }
        return adherent;
    }

    public Adherent findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Adherent adherent = null;
        try {
            adherent = em.find(Adherent.class, id);
        } catch (Exception e) {
            throw e;
        }
        return adherent;
    }

    public List<Adherent> findByMail(String mail) throws Throwable {

        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Adherent> adherents = null;
        try {
            Query q = em.createQuery("SELECT a FROM Adherent a WHERE a.mail='" + mail + "'");
            adherents = (List<Adherent>) q.getResultList();
        } catch (Exception e) {
            throw e;
        }
        
        return adherents;
    }

    public List<Adherent> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Adherent> adherents = null;
        try {
            Query q = em.createQuery("SELECT a FROM Adherent a");
            adherents = (List<Adherent>) q.getResultList();
        } catch (Exception e) {
            throw e;
        }

        return adherents;
    }
}
