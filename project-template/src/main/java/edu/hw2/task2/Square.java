package edu.hw2.task2;


public class Square extends Rectangle {
    public Square(int width) {
        super(width, width);
    }
    @Override
    public Rectangle setWidth(int width) {
        return super.setWidth(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return super.setWidth(height);
    }
}
