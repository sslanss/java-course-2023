package edu.project4.transformations.variations;

import edu.project4.geometry.Point;
import edu.project4.transformations.Transformation;

public class Spiral implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.r();
        double theta = point.theta();
        return new Point((Math.cos(theta) + Math.sin(r)) / r, (Math.sin(theta) - Math.cos(r)) / r);
    }
}
