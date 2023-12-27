package edu.hw5.task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"P@ssw", "#%", "!@#$%^&*|\\^"})
    public void testValidPassword(String password) {
        assertThat(PasswordValidator.isPasswordValid(password)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Password123", "(((Password))))", "123456"})
    public void testInvalidPassword(String password) {
        assertThat(PasswordValidator.isPasswordValid(password)).isFalse();
    }
}
