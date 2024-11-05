package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    public void functionShouldReturnEmptyStringHandlingEmptyInputString() {
        String inputString = "";
        String result = Task4.fixString(inputString);
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    public void functionShouldReturnNullStringHandlingNullInputString() {
        String result = Task4.fixString(null);
        Assertions.assertThat(result).isEqualTo(null);
    }

    @Test
    public void functionShouldReturnCorrectStringHandlingMixedInputString() {
        String inputString = "hTsii  s aimex dpus rtni.g";
        String result = Task4.fixString(inputString);
        Assertions.assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    public void functionShouldReturnCorrectStringHandlingCharInputString() {
        String inputString = "asdfgh";
        String result = Task4.fixString(inputString);
        Assertions.assertThat(result).isEqualTo("safdhg");
    }

    @Test
    public void functionShouldReturnCorrectStringHandlingNumberInputString() {
        String inputString = "123453";
        String result = Task4.fixString(inputString);
        Assertions.assertThat(result).isEqualTo("214335");
    }

    @Test
    public void functionShouldReturnCorrectStringHandlingOddSymbolsInputString() {
        String inputString = "A";
        String result = Task4.fixString(inputString);
        Assertions.assertThat(result).isEqualTo("A");
    }
}
