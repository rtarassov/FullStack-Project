package tarassov.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.ProductRequest;
import tarassov.project.model.Product;
import tarassov.project.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("all")
    public List<Product> findAllProducts() {
        log.info("findAllProducts was called from: [{}]", ProductController.class);
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long productId) {
        log.info("findProductById() was called from: [{}]", ProductController.class);
        var product = productService.findProductById(productId);
        return  product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProductRequest> saveProduct(@RequestBody ProductRequest productRequest) {
        log.info("Trying to save product: [{}]", productRequest);
        var id = productService.saveProductToDB(productRequest);
        return ResponseEntity.created(URI.create("/product/%d"
                .formatted(id)))
                .body(productRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        log.info("deleteProductById() called from: [{}]", ProductController.class);
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
