package edu.hw3;

import edu.hw3.task4.ArabicToRomanConverter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArabicToRomanConverterTest {

    @Test
    public void convertToRomanWithInvalidInputNumber() {
        int inputNumber = 0;
        String result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("");
        inputNumber = 10000;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("");
        inputNumber = -100;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("");
    }

    @Test
    public void convertToRomanWithBaseInputNumber() {
        int inputNumber = 4;
        String result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("IV");
        inputNumber = 10;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("X");
        inputNumber = 400;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("CD");
    }

    @Test
    public void convertToRomanWithCompositeInputNumber() {
        int inputNumber = 111;
        String result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("CXI");
        inputNumber = 42;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("XLII");
        inputNumber = 843;
        result = ArabicToRomanConverter.convertToRoman(inputNumber);
        assertThat(result).isEqualTo("DCCCXLIII");
    }
}
