package edu.hw2.task1;

public record Exponent(Expr value, double base) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(value.evaluate(), base);
    }
}
