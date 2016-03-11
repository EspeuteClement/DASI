import java.sql.Date;

import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {
        // Basic test of the Person class
        Person testPerson = new Person("RENAULT", "Benoit", "7 avenue Jean Capelle, Villeurbanne", new Date(95,07,24), 20);
        System.out.println(testPerson);

        // Basic setup for using persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.insalyon.dasi_TD-JPA_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Add a person in persistence
        testPerson.setId(2L);
        tx.begin();
        em.persist(testPerson);
        tx.commit();
        em.close();

        // Find and display a Person's caracteristics from persistence
        Person foundPerson = em.find(Person.class,2L);
        System.out.println(foundPerson);

        // Find and display all Persons in the table
        Query query = em.createQuery("SELECT e FROM Person e");
        Vector<Person> allPersons = (Vector<Person>) query.getResultList();

        for (int i = 0; i < allPersons.size(); i++)
        {
            System.out.println(allPersons.elementAt(i));
        }
    }
}
