package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Adherent;
import modele.Demande;

public class DemandeDao {

    public void create(Demande demande) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(demande);
        } catch (Exception e) {
            throw e;
        }
    }

    public Demande update(Demande demande) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            demande = em.merge(demande);
        } catch (Exception e) {
            throw e;
        }
        return demande;
    }

    public Demande findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Demande demande = null;
        try {
            demande = em.find(Demande.class, id);
        } catch (Exception e) {
            throw e;
        }
        return demande;
    }

    public List<Demande> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> demandes = null;
        try {
            Query q = em.createQuery("SELECT d FROM Demande d");
            demandes = (List<Demande>) q.getResultList();
        } catch (Exception e) {
            throw e;
        }

        return demandes;
    }
}
