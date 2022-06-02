import entities.Town;
import jdk.dynalink.linker.LinkerServices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class _02_ChangeCasing {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query from_town = entityManager.createQuery("SELECT t FROM town t", Town.class);

        List<Town> resultList = from_town.getResultList();

        for (Town town : resultList) {
            String name = town.getName();

            if(name.length() < 5) {
                String toUpperCase = name.toUpperCase();
                town.setName(toUpperCase);

                entityManager.persist(town);
            }
        }
        entityManager.getTransaction().commit();

    }
}
