package tarassov.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {



}
