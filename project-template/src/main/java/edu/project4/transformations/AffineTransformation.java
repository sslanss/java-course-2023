package edu.project4.transformations;

import edu.project4.geometry.Point;
import java.awt.Color;

public class AffineTransformation implements Transformation {
    private final double a, b, c, d, e, f;

    private Color color = Color.BLACK;
    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public static AffineTransformation newTranslation(double dx, double dy) {
        return new AffineTransformation(1, 0, dx, 0, 1, dy);
    }

    public static AffineTransformation newRotation(double theta) {
        return new AffineTransformation(Math.cos(theta), -Math.sin(theta), 0, Math.sin(theta), Math.cos(theta), 0);
    }

    public static AffineTransformation newScaling(double sx, double sy) {
        return new AffineTransformation(sx, 0, 0, 0, sy, 0);
    }

    @Override
    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }


}
