package tarassov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.ProductDTO;
import tarassov.project.dto.ProductStorageDTO;
import tarassov.project.model.Product;
import tarassov.project.model.ProductType;
import tarassov.project.model.Storage;
import tarassov.project.repository.ProductRepository;
import tarassov.project.repository.StorageRepository;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestProductService {

    @Mock private ServiceValidations serviceValidations;
    @Mock private StorageRepository storageRepository;
    @Mock private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Storage storage;
    private Product product;
    private ProductDTO productDTO;
    private ProductStorageDTO productStorageDTO;

    @BeforeEach
    void setupStorage() {
        storage = new Storage();
        storage.setDescription("Garage");
        storage.setId(5L);
        storage.setName("Drawer");
    }

    @BeforeEach
    void setupProduct() {
        product.setName("DVI cable");
        product.setProductType(ProductType.CABLE);
        product.setDescription("5 meters, pretty old");
        product.setSerialNumber("5DVI5");
        product.setId(10L);
        product.setPrice(7.99);
        product.setPurchaseDate(Date.valueOf("2012-11-22"));
    }

    @BeforeEach
    void setupProductRequest() {
        productDTO = new ProductDTO();
        productDTO.setProductType(ProductType.CABLE);
        productDTO.setName("VGA");
        productDTO.setStorageId(5L);
    }

    /* I tried..
    @BeforeEach
    void setupProductToStorageRequest() {
        productStorageDTO.setProductId(product.getId());
        productStorageDTO.setStorageId(storage.getId());
    }

    @Test
    void testAddingProductToStorage() {
        when(storageRepository.getById(anyLong())).thenReturn(storage);
        when(productRepository.getById(anyLong())).thenReturn(product);
        Assertions.assertTrue(productService.addProductToStorage(productStorageDTO));
        verify(productRepository.getById(10L));
    }
    */

    @Test
    void testSavingProductToDB() {
        when(storageRepository.getById(anyLong())).thenReturn(storage);
        when(serviceValidations.isValidCharacters(anyString())).thenReturn(false);
        Assertions.assertThrows(ResponseStatusException.class, () -> productService.saveProductToDB(productDTO));
        verify(storageRepository).getById(anyLong());
    }




}
