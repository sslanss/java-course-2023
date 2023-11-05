package edu.project2.maze;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
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

    public Cell getCell(Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()];
    }

    public void setCell(Coordinate coordinate, Cell.Type type) {
        grid[coordinate.row()][coordinate.col()] = new Cell(coordinate.row(), coordinate.col(), type);
    }

    public boolean isValidLocation(Coordinate coordinate) {
        return coordinate.row() >= 0 && coordinate.row() <= height - 1
            && coordinate.col() >= 0 && coordinate.col() <= width - 1;
    }

    public boolean isWall(Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()].type().equals(Cell.Type.WALL);
    }
}
