package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tarassov.project.model.UserType;

@Slf4j
@Service
public class ServiceValidations {

    // TODO:
    // Different entities have different min/max lengths.
    // Figure it out how to make it dynamic or make a validator for each entity column
    // that requires validations.

    public boolean isValidCharacters(String input) {
        if (input.matches("[a-zA-Z0-9 ]{3,20}+")) {
            log.info("Input is valid.");
            return true;
        } else {
            log.info("Input must contain at least 3 letters a-Z and numbers 0-9.");
            log.info("Input cannot be longer than 30 characters.");
            throw new IllegalArgumentException("Name is not 3 - 30 characters or contains symbols.");
        }
    }
    public boolean isValidCharactersIgnoreLength(String input) {
        if (input.matches("[a-zA-Z0-9 ]+")) {
            log.info("Input is valid.");
            return true;
        }
        log.info("Input must contain at least 3 letters a-Z and numbers 0-9.");
        log.info("Input cannot be longer than 30 characters.");
        return false;
    }

    public boolean isUserTypeValid(String enumValue) {
        for (UserType userType : UserType.values()) {
            if (enumValue.matches(userType.toString())) {
                return true;
            } else {
                throw new IllegalArgumentException("User type doesn't match registry.");
            }
        }
        return false;
    }
}
