package edu.project4.transformations.variations;

import edu.project4.geometry.Point;
import edu.project4.transformations.Transformation;

public class Sinusoid implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sin(point.x()),
            Math.sin(point.y())
        );
    }
}
