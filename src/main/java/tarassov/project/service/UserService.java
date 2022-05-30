package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.UserDTO;
import tarassov.project.model.User;
import tarassov.project.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long saveUserToDB(UserDTO userDTO) {
        log.info("User to save: [{}]", userDTO);

        try {
            var userObject = new User();
            userObject.setUsername(userDTO.getUsername());
            userObject.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userObject.setEmail(userDTO.getEmail());
            userObject.setName(userDTO.getName());
            userObject.setProductLimit(userDTO.getProductLimit());
            userObject.setUserType(userObject.getUserType());
            userObject.setBirthDate(userDTO.getBirthDate());

            return userObject.getId();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> findAllUsers() {
        log.info("Finding all users");
        return userRepository.findAll();
    }
}
