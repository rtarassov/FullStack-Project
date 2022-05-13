package tarassov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tarassov.project.model.DemoModel;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Long> {

}
