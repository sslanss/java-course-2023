package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {

    @Test
    void rectangleAreaShouldReturnCorrectResult() {
        Rectangle rect = new Rectangle(20, 10);
        Assertions.assertThat(rect.area()).isEqualTo(200);
        rect = new Square(20);
        Assertions.assertThat(rect.area()).isEqualTo(400);
    }

    @Test
    void rectangleShouldBeImmutable() {
        Rectangle rect = new Rectangle(20, 10);
        rect.setWidth(20);
        rect.setHeight(30);
        Assertions.assertThat(rect.area()).isEqualTo(200.0);
        rect = new Square(20);
        rect.setWidth(30);
        Assertions.assertThat(rect.area()).isEqualTo(400.0);
    }

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(20, 40)),
            Arguments.of(new Square(20))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleAreaShouldReturnSameResult(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        Assertions.assertThat(rect.area()).isEqualTo(200.0);
    }

}
