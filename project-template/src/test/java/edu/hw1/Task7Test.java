package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    public void functionShouldReturnCorrectResult() {
        int result = Task7.rotateLeft(17,2);
        Assertions.assertThat(result).isEqualTo(6);
         result = Task7.rotateRight(8,1);
        Assertions.assertThat(result).isEqualTo(4);
        result = Task7.rotateLeft(16,1);
        Assertions.assertThat(result).isEqualTo(1);
    }
}
