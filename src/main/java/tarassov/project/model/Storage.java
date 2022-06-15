package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(min = 3, max = 50)
    private String description;

    @OneToMany
    private List<Storage> subStorageList;

}
