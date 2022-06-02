import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _13_RemoveTowns {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String townName = scanner.nextLine();

        Town town = entityManager.createQuery(" SELECT t FROM Town t" +
                " WHERE t.name = :tName", Town.class)
                .setParameter("tName", townName)
                .getSingleResult();



        List<Address> addresses = entityManager
                .createQuery("select a from Address a " +
                        "where a.town.name = :tName", Address.class)
                .setParameter("tName", townName)
                .getResultList();

        String output = String.format("%d address in %s deleted",addresses.size(),townName);
        System.out.println(output);

        addresses.forEach( address -> {
            address.getEmployees().forEach( employee -> employee.setAddress(null));
            address.setTown(null);
            entityManager.remove(address);
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
