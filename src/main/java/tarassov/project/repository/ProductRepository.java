package tarassov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
