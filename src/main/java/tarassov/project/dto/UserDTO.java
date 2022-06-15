package tarassov.project.dto;

import lombok.Data;
import tarassov.project.model.UserType;

import java.sql.Date;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String email;
    private Long productLimit;
    private UserType userType;
    private Date birthDate;

}
