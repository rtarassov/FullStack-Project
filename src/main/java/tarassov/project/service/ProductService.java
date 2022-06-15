package tarassov.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.ProductDTO;
import tarassov.project.dto.ProductStorageDTO;
import tarassov.project.model.Product;
import tarassov.project.repository.PictureRepository;
import tarassov.project.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;
    private final ServiceValidations serviceValidations;
    private final PictureRepository pictureRepository;

    public boolean addProductToStorage(ProductStorageDTO productStorageDTO) {
        try {
            var productObject = productRepository.getById(productStorageDTO.getProductId());
            var storageObject = storageService.getStorageById(productStorageDTO.getStorageId());

            productObject.setStorage(storageObject);
            productRepository.save(productObject);
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Long saveProductToDB(ProductDTO productDTO) {
        log.info("Entity to save: [{}]", productDTO);

        try {
            var productObject = new Product();
            var storageObject = storageService.getStorageById(productDTO.getStorageId());

            if (serviceValidations.isValidCharacters(productDTO.getName())) {
                productObject.setName(productDTO.getName());
            }
            productObject.setSerialNumber(productDTO.getSerialNumber());

            if (productDTO.getPictureId() != null) {
                productObject.setPicture(pictureRepository.getById(productDTO.getPictureId()));
            }
            if (productDTO.getDescription() != null) {
                productObject.setDescription(productDTO.getDescription());
            } else {
                throw new IllegalArgumentException("You must provide a description");
            }
            productObject.setProductType(productDTO.getProductType());

            if (productDTO.getPrice() > 0) {
                productObject.setPrice(productDTO.getPrice());
            } else {
                throw new IllegalArgumentException("Price must be higher than 0.");
            }
            productObject.setPurchaseDate(productDTO.getPurchaseDate());
            productObject.setStorage(storageObject);

            productRepository.save(productObject);
            log.info("Product saved successfully.");
            return productObject.getId();
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
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

    @Transactional
    public Long updateProductById(Long id, ProductDTO productDTO) {
        log.info("Trying to update product [{}]", productDTO);

        try {
            var productObject = new Product();
            var storageObject = storageService.getStorageById(productDTO.getStorageId());

            productObject.setId(id);

            if (serviceValidations.isValidCharacters(productDTO.getName())) {
                productObject.setName(productDTO.getName());
            }
            productObject.setSerialNumber(productDTO.getSerialNumber());

            if (productDTO.getPictureId() != null) {
                productObject.setPicture(pictureRepository.getById(productDTO.getPictureId()));
            }
            productObject.setDescription(productDTO.getDescription());
            productObject.setProductType(productDTO.getProductType());

            if (productDTO.getPrice() > 0) {
                productObject.setPrice(productDTO.getPrice());
            } else {
                throw new IllegalArgumentException("Price must be higher than 0.");
            }
            productObject.setPurchaseDate(productDTO.getPurchaseDate());
            productObject.setStorage(storageObject);

            productRepository.save(productObject);
            log.info("Product saved successfully.");
            return productObject.getId();
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
