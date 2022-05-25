package tarassov.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
