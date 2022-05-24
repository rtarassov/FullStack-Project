package tarassov.project.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String serialNumber;
    private Long pictureId;
    private String description;
    private String productType;
    private Double value;
    private String buyDate;
    private Long storageId;
}
