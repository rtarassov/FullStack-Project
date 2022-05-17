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
    private final StorageService storageService;

    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping("all-products")
    public List<Product> findAllProducts() {
        log.info("findAllProducts was called from: [{}]", ProductController.class.toString());
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long productId) {
        log.info("findProductById() was called from: [{}]", ProductController.class.toString());
        var product = productService.findProductById(productId);
        return  product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: Fix 400 BAD_REQUEST ResponseStatusException
    // Sample RequestBody below:
    //{
    //    "name": "VGA to HDMI",
    //        "serialNumber": "VGA-HDMI-123456789",
    //        "picture_path": "src/main/resources/pictures/products/VGA-HDMI.jpg",
    //        "description": "VGA to HDMI adapter, not opened.",
    //        "productType": "ADAPTER",
    //        "value": 15.29,
    //        "buyDate": "23.05.2021",
    //        "storage": null
    //}
    @PostMapping
    public ResponseEntity<?> saveProductToDB(@RequestBody ProductRequest productRequest) {
        log.info("Trying to save product: [{}]", productRequest);
        var id = productService.saveProductToDB(productRequest);
        return ResponseEntity.created(URI.create("/product/%d"
                .formatted(id)))
                .body(productRequest);
    }
}
