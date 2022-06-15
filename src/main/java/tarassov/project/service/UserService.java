package tarassov.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.UserDTO;
import tarassov.project.dto.UserStorageDTO;
import tarassov.project.model.User;
import tarassov.project.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final ServiceValidations serviceValidations;
    private final StorageService storageService;

    public Long saveUserToDB(UserDTO userDTO) {
        log.info("User to save: [{}]", userDTO);

        try {
            var userObject = new User();

            if (serviceValidations.isValidCharacters(userDTO.getUsername())) {
                userObject.setUsername(userDTO.getUsername());
            }

            userObject.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userObject.setEmail(userDTO.getEmail());

            if (serviceValidations.isValidCharacters(userDTO.getName())) {
                userObject.setName(userDTO.getName());
            }

            userObject.setProductLimit(userDTO.getProductLimit());

            if (serviceValidations.isUserTypeValid(String.valueOf(userDTO.getUserType()))) {
                userObject.setUserType(userObject.getUserType());
            }

            userObject.setBirthDate(userDTO.getBirthDate());

            return userObject.getId();
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<User> findAllUsers() {
        var users = userRepository.findAll();
        log.info("Found users: [{}]", users);
        return users;
    }

    public Optional<User> findUserById(Long id) {
        var user = userRepository.findById(id);
        log.info("Found user: [{}]", user);
        return user;
    }

    public boolean deleteUserById(Long id) {
        boolean result = false;

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    public Long updateUserById(Long id, UserDTO userDTO) {
        log.info("Trying to update user [{}]", userRepository.getById(id));
        log.info("With data: [{}]", userDTO);

        try {
            var userObject = userRepository.getById(id);

            if (serviceValidations.isValidCharacters(userDTO.getName())) {
                userObject.setName(userDTO.getName());
            }

            if (serviceValidations.isValidCharacters(userDTO.getUsername())) {
                userObject.setUsername(userDTO.getUsername());
            }

            // TODO:
            // Found something like mapstruct.org
            // Will try to implement one day
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            userObject.setEmail(userDTO.getEmail());
            userObject.setProductLimit(userDTO.getProductLimit());
            userObject.setUserType(userDTO.getUserType());
            userObject.setBirthDate(userDTO.getBirthDate());

            userRepository.save(userObject);
            return userObject.getId();
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void assignStorageToUser(UserStorageDTO userStorageDTO) {
        try {
            var userObject = userRepository.getById(userStorageDTO.getUserId());
            var storageObject = storageService.getStorageById(userStorageDTO.getStorageId());

            userObject.getStorage().add(storageObject);
            userRepository.save(userObject);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}

