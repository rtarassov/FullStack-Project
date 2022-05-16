package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String serialNumber;
    private String picture_path;
    private String description;
    private ProductType productType;
    private Double value;
    private Date buyDate;

    @ManyToOne
    private Storage storage;
}
