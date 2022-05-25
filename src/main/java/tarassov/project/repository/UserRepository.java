package tarassov.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tarassov.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
