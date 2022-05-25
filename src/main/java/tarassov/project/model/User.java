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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String name;

    @Email
    private String email;
    private Long productLimit;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date birthDate;


    @ManyToMany
    private List<Storage> storage;
}
