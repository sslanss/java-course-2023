package edu.project4.transformations.variations;

import edu.project4.geometry.Point;
import edu.project4.transformations.Transformation;

public class Spherical implements Transformation {
    @Override public Point apply(Point point) {
        return new Point(
            point.x() / (point.x() * point.x() + point.y() * point.y()),
            point.y() / (point.x() * point.x() + point.y() * point.y())
        );
    }
}
