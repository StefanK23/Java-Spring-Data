import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _12_EmployeeMaximumSalaries {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();


        entityManager.createQuery(" SELECT e.department.name, MAX(e.salary) " +
                " FROM Employee e " +
                " GROUP BY e.department.id " +
                " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(e -> System.out.println(e[0] + " - " + e[1]));
    }
}
