package _02;

import _02.Entities.Customer;
import _02.Entities.Product;
import _02.Entities.Sale;
import _02.Entities.StoreLocation;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class _02Main {

    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CodeFirstEx");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product("asd", 123, BigDecimal.TEN);
        Customer customer = new Customer("asd","asd","asd");
        StoreLocation storeLocation = new StoreLocation("location");

        Sale sale=  new Sale(product,customer,storeLocation);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
