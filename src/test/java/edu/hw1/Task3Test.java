package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void functionShouldReturnFalseHandlingEmptyArrays() {
        int[] first = new int[0];
        int[] second = new int[0];
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnFalseHandlingOneEmptyArray() {
        int[] first = new int[0];
        int[] second = {1, 2, 3, 4};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
        int[] anotherFirst = {1, 2, 3, 4};
        int[] anotherSecond = new int[0];
        result = Task3.isNestable(anotherFirst, anotherSecond);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnFalseHandlingOneNullArray() {
        int[] first = null;
        int[] second = {1, 2, 3, 4};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
        int[] anotherFirst = {1, 2, 3, 4};
        int[] anotherSecond = null;
        result = Task3.isNestable(anotherFirst, anotherSecond);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnFalseHandlingNullArrays() {
        int[] first = null;
        int[] second = null;
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueIfArraysAreAscendingSortedAndFirstArrayCouldBeNested() {
        int[] first = {1, 2, 3, 4};
        int[] second = {0, 6};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseIfIfArraysAreAscendingSortedAndFirstArrayCouldNotBeNested() {
        int[] first = {1, 2, 3, 4};
        int[] second = {2, 3};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueIfArraysAreDescendingSortedAndFirstArrayCouldBeNested() {
        int[] first = {4, 3, 2, 1};
        int[] second = {6, 0};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseIfArraysAreDescendingSortedAndFirstArrayCouldNotBeNested() {
        int[] first = {4, 3, 2, 1};
        int[] second = {3, 2};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnFalseIfArraysMinMaxAreTheSame() {
        int[] first = {9, 9, 8};
        int[] second = {8, 9};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueIfBothArraysAreTheSame() {
        int[] first = {1, 1, 1, 1};
        int[] second = {1, 1, 1, 1};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueIfArraysAreUnsortedAndFirstArrayCouldBeNested() {
        int[] first = {4, 2, 3, 1};
        int[] second = {0, 6, 5};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseIfArraysAreUnsortedAndFirstArrayCouldNotBeNested() {
        int[] first = {4, 2, 3, 1};
        int[] second = {3, 2};
        boolean result = Task3.isNestable(first, second);
        Assertions.assertThat(result).isEqualTo(false);
    }
}
