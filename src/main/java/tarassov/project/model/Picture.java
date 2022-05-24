package tarassov.project.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Picture {

    @Id
    @GeneratedValue
    private Integer id;

    @Lob
    @NotNull
    private byte[] content;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
}
