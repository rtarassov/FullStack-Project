package tarassov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarassov.project.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
