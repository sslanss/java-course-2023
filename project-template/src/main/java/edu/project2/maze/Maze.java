package edu.project2.maze;

import org.jetbrains.annotations.NotNull;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width of Maze must be positive.");
        }
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(new Coordinate(row, col), Cell.Type.WALL);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(@NotNull Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()];
    }

    public void setCell(@NotNull Coordinate coordinate, Cell.Type type) {
        grid[coordinate.row()][coordinate.col()] = new Cell(coordinate, type);
    }

    public boolean isValidLocation(@NotNull Coordinate coordinate) {
        return coordinate.row() >= 0 && coordinate.row() <= height - 1
            && coordinate.col() >= 0 && coordinate.col() <= width - 1;
    }

    public boolean isWall(@NotNull Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()].type().equals(Cell.Type.WALL);
    }
}
