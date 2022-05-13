package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DemoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}
