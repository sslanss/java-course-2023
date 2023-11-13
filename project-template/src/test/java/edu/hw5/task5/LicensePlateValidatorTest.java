package edu.hw5.task5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LicensePlateValidatorTest {

    @Test
    public void testValidLicensePlate() {
        assertThat(LicensePlateValidator.isLicenseValidValid("А123ВЕ777")).isTrue();

        assertThat(LicensePlateValidator.isLicenseValidValid("О777ОО177")).isTrue();
    }

    @Test
    public void testInvalidLicensePlate() {
        assertThat(LicensePlateValidator.isLicenseValidValid("")).isFalse();

        assertThat(LicensePlateValidator.isLicenseValidValid("123АВЕ777")).isFalse();

        assertThat(LicensePlateValidator.isLicenseValidValid("А12ВГ77")).isFalse();

        assertThat(LicensePlateValidator.isLicenseValidValid("А123ВГ7777")).isFalse();

        assertThat(LicensePlateValidator.isLicenseValidValid("А123ВЯ777")).isFalse();
    }
}
