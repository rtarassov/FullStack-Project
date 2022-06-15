package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @NotNull
    private byte[] content;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
}
