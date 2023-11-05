package edu.project2.maze;

public record Coordinate(int row, int col) {
    public Coordinate getNextCoordinate(int i, int j) {
        return new Coordinate(row() + i, col() + j);
    }
}
