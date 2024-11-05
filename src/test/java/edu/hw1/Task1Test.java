package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    public void functionShouldReturnMinusOneHandlingEmptyInputString() {
        String inputString = "";
        int result = Task1.minutesToSeconds(inputString);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void functionShouldReturnMinusOneHandlingNullInputString() {
        int result = Task1.minutesToSeconds(null);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void functionShouldReturnMinusOneHandlingIncorrectInputString() {
        String inputString = "10:60";
        int result = Task1.minutesToSeconds(inputString);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void functionShouldReturnCorrectValueHandlingCorrectInputString() {
        String inputString = "999:59";
        int result = Task1.minutesToSeconds(inputString);
        Assertions.assertThat(result).isEqualTo(59999);
    }

    @Test
    public void functionShouldReturnMinusOneHandlingIncorrectInputStringFormat() {
        String inputString = "999999";
        int result = Task1.minutesToSeconds(inputString);
        Assertions.assertThat(result).isEqualTo(-1);
    }
}
