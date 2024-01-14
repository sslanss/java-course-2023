package edu.project4.geometry;

public record Point(double x, double y) {
    public double r() {
        return Math.sqrt(x * x + y * y);
    }

    public double theta() {
        return Math.atan(x / y);
    }
}
