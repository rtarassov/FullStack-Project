package tarassov.project.dto;

import lombok.Data;
import tarassov.project.model.ProductType;

import java.sql.Date;

@Data
public class ProductDTO {
    private String name;
    private String serialNumber;
    private Long pictureId;
    private String description;
    private ProductType productType;
    private Double price;
    private Date purchaseDate;
    private Long storageId;
}
