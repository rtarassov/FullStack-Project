package tarassov.project.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String serialNumber;
    private Long pictureId;
    private String description;
    private String productType;
    private Double price;
    private String purchaseDate;
    private Long storageId;
}
