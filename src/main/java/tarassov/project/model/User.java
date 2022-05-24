package tarassov.project.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password; // TODO: Store passwords better
    private String name;

    @Email
    private String email;
    private int productLimit;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date birthDate;


    @ManyToMany
    private List<Storage> storage;
}
