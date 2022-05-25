package tarassov.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class TestServiceValidations {

    @InjectMocks
    private ServiceValidations serviceValidations;

    private static final String CORRECT_INPUT = "AdapterForVGA";
    private static final String INPUT_WITH_SYMBOL = "Abc52(";
    private static final String INPUT_TOO_SHORT = "AB";
    private static final String INPUT_TOO_LONG = "123456789012345678901"; //21

    @Test
    void checkForCharacters_validInput_returnsTrue() {
        Assertions.assertTrue(serviceValidations.checkForCharacters(CORRECT_INPUT));
    }

    @Test
    void checkForCharacters_inValidInputTooLong_returnsFalse() {
        Assertions.assertFalse(serviceValidations.checkForCharacters(INPUT_TOO_LONG));
    }

    @ParameterizedTest
    @ValueSource(strings = {INPUT_WITH_SYMBOL, INPUT_TOO_LONG, INPUT_TOO_SHORT})
    void checkForCharacters_inValidInput_returnsFalse(String input) {
        Assertions.assertFalse(serviceValidations.checkForCharacters(input));

    }
}
