package tarassov.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.ProductDTO;
import tarassov.project.dto.ProductStorageDTO;
import tarassov.project.model.Product;
import tarassov.project.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long productId) {
        var product = productService.findProductById(productId);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        var id = productService.saveProductToDB(productDTO);
        return ResponseEntity.created(URI.create("/product/%d"
                .formatted(id)))
                .body(productDTO);
    }

    @PutMapping("/add-to-storage")
    public ResponseEntity<Void> addProductToStorage(@RequestBody ProductStorageDTO productStorageDTO) {
        productService.addProductToStorage(productStorageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") Long id,
                                               @RequestBody ProductDTO productDTO) {

        var productId = productService.updateProductById(id, productDTO);

        return ResponseEntity
                .created(URI.create("/product/update/%d"
                        .formatted(productId)))
                        .body(productDTO);
    }


}
