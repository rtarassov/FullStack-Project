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
import tarassov.project.model.ProductType;
import tarassov.project.model.Storage;
import tarassov.project.repository.StorageRepository;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestProductService {

    @Mock private ServiceValidations serviceValidations;
    @Mock private StorageRepository storageRepository;
    @InjectMocks
    private ProductService productService;

    private Storage storage;
    private ProductDTO productDTO;

    @BeforeEach
    void setupStorage() {
        storage = new Storage();
        storage.setDescription("Garage");
        storage.setId(5L);
        storage.setName("Drawer");
    }

    @BeforeEach
    void setupProductRequest() {
        productDTO = new ProductDTO();
        productDTO.setProductType(ProductType.CABLE);
        productDTO.setName("VGA");
        productDTO.setStorageId(5L);
    }

    @Test
    void testSavingProductToDB() {
        when(storageRepository.getById(anyLong())).thenReturn(storage);
        when(serviceValidations.checkForCharacters(anyString())).thenReturn(false);
        Assertions.assertThrows(ResponseStatusException.class, () -> productService.saveProductToDB(productDTO));
        verify(storageRepository).getById(anyLong());
    }




}
