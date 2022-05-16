package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

}
