package tarassov.project.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // TODO: Store passwords better
    private String name;
    private String email;
    private int productLimit;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Date birthDate;


    @ManyToMany
    private List<Storages> storages;
}
