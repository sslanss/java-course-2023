package edu.hw2.task1;

public record Addition(Expr x, Expr y) implements Expr {

    @Override
    public double evaluate() {
        return x.evaluate() + y.evaluate();
    }

}
