import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _10_IncreaseSalaries {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("update Employee e " +
                " set e.salary = e.salary * 1.12 " +
                " where e.department.id = 1 or e.department.id = 2 " +
                " or e.department.id = 4 or e.department.id = 11").executeUpdate();

        entityManager.createQuery("select e from Employee e " +
                "where e.department.id = 1 or e.department.id = 2 " +
                "or e.department.id = 4 or e.department.id = 11", Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf("%s %s ($%.2f) %n",
                                e.getFirstName(),
                                e.getLastName(),
                                e.getSalary()
                ));

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
