package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByPassword(String password);

    Optional<User> findByUsername(String username);


    @Query("SELECT u FROM User AS u JOIN Post AS p ON p.user.id = u.id GROUP BY u.id ORDER BY count(p.id) DESC")
    List<User> findAllOrderByPostCountAsc();
}
