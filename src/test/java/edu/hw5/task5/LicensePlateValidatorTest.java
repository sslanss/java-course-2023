package edu.hw5.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LicensePlateValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "А123ВЕ77"})
    public void testValidLicensePlate(String licensePlate) {
        assertThat(LicensePlateValidator.isLicenseValidValid(licensePlate)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123АВЕ777", "А12ВГ77", "А123ВГ7777", "А123ВЯ777"})
    public void testInvalidLicensePlate(String licensePlate) {
        assertThat(LicensePlateValidator.isLicenseValidValid(licensePlate)).isFalse();
    }
}
