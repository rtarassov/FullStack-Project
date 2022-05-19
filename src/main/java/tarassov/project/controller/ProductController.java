package tarassov.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.ProductRequest;
import tarassov.project.model.Product;
import tarassov.project.service.ProductService;
import tarassov.project.service.StorageService;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService; // Will be used to assign a storage location to a product if storage is not yet set

    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping("all-products")
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
    public ResponseEntity<?> saveProductToDB(@RequestBody ProductRequest productRequest) {
        log.info("Trying to save product: [{}]", productRequest);
        var id = productService.saveProductToDB(productRequest);
        return ResponseEntity.created(URI.create("/product/%d"
                .formatted(id)))
                .body(productRequest);
    }
    // Sample RequestBody below:
    /*
     {
     "name": "MiniDP to DP",
     "serialNumber": "MINIDP-DP123456789",
     "description": "MiniDP to DP adapter, not opened.",
     "productType": "ADAPTER",
     "value": 19.39,
     "buyDate": "2022-07-22",
     "storageId": 1
     }
     I left out picture path intentionally
     */

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
