package tarassov.project.dto;

import lombok.Data;
import tarassov.project.model.ProductType;

@Data
public class ProductDTO {

    private String name;
    private String serialNumber;
    private Long pictureId;
    private String description;
    private ProductType productType;
    private Double price;
    private String purchaseDate;
    private Long storageId;
}
