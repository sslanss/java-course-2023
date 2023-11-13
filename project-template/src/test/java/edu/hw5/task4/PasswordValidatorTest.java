package edu.hw5.task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTest {

    @Test
    public void testValidPassword() {
        assertThat(PasswordValidator.isPasswordValid("P@ssw")).isTrue();

        assertThat(PasswordValidator.isPasswordValid("#%")).isTrue();

        assertThat(PasswordValidator.isPasswordValid("!@#$%^&*|\\^")).isTrue();
    }

    @Test
    public void testInvalidPassword() {
        assertThat(PasswordValidator.isPasswordValid("")).isFalse();

        assertThat(PasswordValidator.isPasswordValid("Password123")).isFalse();

        assertThat(PasswordValidator.isPasswordValid("(((Password))))")).isFalse();

        assertThat(PasswordValidator.isPasswordValid("123456")).isFalse();
    }
}
