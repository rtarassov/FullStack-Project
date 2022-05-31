package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceValidations {

    public boolean checkForCharacters(String input) {
        if (input.matches("[a-zA-Z0-9 ]{3,20}+")) {
            log.info("Input is valid.");
            return true;
        }
        log.info("Input must contain at least 3 letters a-Z and numbers 0-9.");
        log.info("Input cannot be longer than 30 characters.");
        return false;
    }
    public boolean checkForCharactersIgnoreLength(String input) {
        if (input.matches("[a-zA-Z0-9 ]+")) {
            log.info("Input is valid.");
            return true;
        }
        log.info("Input must contain at least 3 letters a-Z and numbers 0-9.");
        log.info("Input cannot be longer than 30 characters.");
        return false;
    }
}
