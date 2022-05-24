package tarassov.project.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5, max = 20)
    private String serialNumber;

    private String picture_path;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotNull
    private Double value;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date buyDate;

    @ManyToOne
    private Storages storages;
}
