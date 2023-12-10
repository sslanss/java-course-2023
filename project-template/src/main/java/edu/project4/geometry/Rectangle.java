package edu.project4.geometry;

public record Rectangle(Point leftCorner, int width, int height) {

    public boolean contains(Point point){
        return point.x() >= leftCorner.x() && point.x()<= leftCorner().x() + width
            && point.y() >= leftCorner.y() && point.y()<= leftCorner().y() + height;
    }
}
