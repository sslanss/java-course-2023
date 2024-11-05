package edu.hw2;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    public void constantEvaluateShouldReturnCorrectResult() {
        Constant constant = new Constant(5.25);
        Assertions.assertThat(constant.evaluate()).isEqualTo(5.25);
        constant = new Constant(-5.25);
        Assertions.assertThat(constant.evaluate()).isEqualTo(-5.25);
    }

    @Test
    public void negativeEvaluateShouldReturnCorrectResult() {
        Negate negative = new Negate(new Constant(5));
        Exponent exponent = new Exponent(new Constant(5), 2);
        Addition addition = new Addition(new Constant(5), new Constant(5));
        Multiplication multiplication = new Multiplication(new Constant(5), new Constant(5));
        Assertions.assertThat(negative.evaluate()).isEqualTo(-5);
        negative = new Negate(negative);
        Assertions.assertThat(negative.evaluate()).isEqualTo(5);
        negative = new Negate(exponent);
        Assertions.assertThat(negative.evaluate()).isEqualTo(-25);
        negative = new Negate(addition);
        Assertions.assertThat(negative.evaluate()).isEqualTo(-10);
        negative = new Negate(multiplication);
        Assertions.assertThat(negative.evaluate()).isEqualTo(-25);
    }

    @Test
    public void exponentEvaluateShouldReturnCorrectResult() {
        Negate negative = new Negate(new Constant(5));
        Exponent exponent = new Exponent(new Constant(5), 2);
        Addition addition = new Addition(new Constant(5), new Constant(5));
        Multiplication multiplication = new Multiplication(new Constant(5), new Constant(5));
        Assertions.assertThat(exponent.evaluate()).isEqualTo(25);
        exponent = new Exponent(exponent, 2);
        Assertions.assertThat(exponent.evaluate()).isEqualTo(625);
        exponent = new Exponent(negative, 2);
        Assertions.assertThat(exponent.evaluate()).isEqualTo(25);
        exponent = new Exponent(addition, 2);
        Assertions.assertThat(exponent.evaluate()).isEqualTo(100);
        exponent = new Exponent(multiplication, 2);
        Assertions.assertThat(exponent.evaluate()).isEqualTo(625);
    }

    @Test
    public void multiplicationEvaluateShouldReturnCorrectResult() {
        Negate negative = new Negate(new Constant(5));
        Exponent exponent = new Exponent(new Constant(5), 2);
        Addition addition = new Addition(new Constant(5), new Constant(5));
        Multiplication multiplication = new Multiplication(new Constant(5), new Constant(5));
        Assertions.assertThat(multiplication.evaluate()).isEqualTo(25);
        multiplication = new Multiplication(multiplication, multiplication);
        Assertions.assertThat(multiplication.evaluate()).isEqualTo(625);
        multiplication = new Multiplication(negative, new Constant(5));
        Assertions.assertThat(multiplication.evaluate()).isEqualTo(-25);
        multiplication = new Multiplication(exponent, new Constant(5));
        Assertions.assertThat(multiplication.evaluate()).isEqualTo(125);
        multiplication = new Multiplication(addition, new Constant(5));
        Assertions.assertThat(multiplication.evaluate()).isEqualTo(50);
    }

    @Test
    public void additionEvaluateShouldReturnCorrectResult() {
        Negate negative = new Negate(new Constant(5));
        Exponent exponent = new Exponent(new Constant(5), 2);
        Addition addition = new Addition(new Constant(5), new Constant(5));
        Multiplication multiplication = new Multiplication(new Constant(5), new Constant(5));
        Assertions.assertThat(addition.evaluate()).isEqualTo(10);
        addition = new Addition(addition, addition);
        Assertions.assertThat(addition.evaluate()).isEqualTo(20);
        addition = new Addition(negative, new Constant(5));
        Assertions.assertThat(addition.evaluate()).isEqualTo(0);
        addition = new Addition(exponent, new Constant(5));
        Assertions.assertThat(addition.evaluate()).isEqualTo(30);
        addition = new Addition(multiplication, new Constant(5));
        Assertions.assertThat(addition.evaluate()).isEqualTo(30);
    }
}
