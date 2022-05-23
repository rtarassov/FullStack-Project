package tarassov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.Storages;

public interface StorageRepository extends JpaRepository<Storages, Long> {
}
