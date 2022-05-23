package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Storages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

}
