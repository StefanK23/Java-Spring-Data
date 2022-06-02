package softuni.exam.instagraphlite.repository;

//ToDo

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.Picture;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Optional<Picture> findByPath(String path);


    Optional<Picture> findByPathEquals(String profilePicture);


    List<Picture> findAllBySizeGreaterThanOrderBySizeAsc(double size);
}
