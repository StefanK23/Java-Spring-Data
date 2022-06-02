import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;

public class _08_GetEmployeeWithProject {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        int employeeId = Integer.parseInt(scanner.nextLine());

        entityManager.createQuery(" SELECT e from Employee e" +
                " WHERE e.id = :employee ", Employee.class)
                .setParameter("employee", employeeId)
                .getResultStream().forEach( e -> {
            System.out.printf("%s %s - %s", e.getFirstName(), e.getLastName(), e.getJobTitle());
            e.getProjects().stream().sorted(Comparator.comparing(Project::getName))
                    .forEach(p -> System.out.println(p.getName()));
        });

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
