package tarassov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
