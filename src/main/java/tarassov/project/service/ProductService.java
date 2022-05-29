package tarassov.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.ProductRequest;
import tarassov.project.dto.ProductStorageRequest;
import tarassov.project.model.Product;
import tarassov.project.repository.ProductRepository;
import tarassov.project.repository.StorageRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StorageRepository storageRepository;
    private final ServiceValidations serviceValidations;

    // Doesn't work
    public void addProductToStorage(ProductStorageRequest productStorageRequest) {
        try {
            var storageObject = storageRepository.getById(productStorageRequest.getStorageId());
            // TODO:
            // Need to rethink the database, because here I would need to have a List<Product> in Storage,
            // But it messes up the tables.
            // storageObject.getProduct().add(productObject);
            storageRepository.save(storageObject);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Long saveProductToDB(ProductRequest productRequest) {
        log.info("Entity to save: [{}]", productRequest);

        try {
            var storageObject = storageRepository.getById(productRequest.getStorageId());
            var productObject = new Product();

            if (serviceValidations.checkForCharacters(productRequest.getName())) {
                productObject.setName(productRequest.getName());
            } else {
                throw new IllegalArgumentException("Name is not at least 3 characters or contains symbols.");
            }
            productObject.setSerialNumber(productRequest.getSerialNumber());
            productObject.setDescription(productRequest.getDescription());
            productObject.setProductType(productRequest.getProductType());
            productObject.setPrice(productRequest.getPrice());
            productObject.setStorage(storageObject);
            productObject.setPurchaseDate(Date.valueOf(productRequest.getPurchaseDate()));

            productRepository.save(productObject);
            return productObject.getId();
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Transactional
    public boolean deleteProductById(Long id) {
        log.info("Trying to delete Product by id: [{}]", id);

        boolean result = false;
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    public Optional<Product> findProductById(Long id) {
        log.info("Trying to find product by id: [{}]", id);
        var product = productRepository.findById(id);

        log.info("Found product from database: [{}]", product);
        return product;
    }

    public List<Product> findAllProducts() {
        log.info("Trying to find all products from database");
        var products = productRepository.findAll();
        log.info("Products found: " + products);
        return products;
    }
}
