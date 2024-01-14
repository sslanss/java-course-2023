package edu.project4.transformations.variations;

import edu.project4.geometry.Point;
import edu.project4.transformations.Transformation;

public class Hyperbolic implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.r();
        double theta = point.theta();
        return new Point(Math.sin(theta) / r, r * Math.cos(theta));
    }
}
