package edu.project2.generators;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.Random;

public class DFSGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        generateMaze(maze, new Coordinate(0, 0));
        return maze;
    }

    private void generateMaze(Maze maze, Coordinate coordinate) {
        maze.setCell(coordinate, Cell.Type.PASSAGE);
        int[][] directions = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
        shuffleDirections(directions);
        for (int[] direction : directions) {
            Coordinate newCoordinate = new Coordinate(coordinate.row() + direction[0], coordinate.col() + direction[1]);
            if (maze.isValidLocation(newCoordinate) && maze.getCell(newCoordinate).type() == Cell.Type.WALL) {
                Coordinate wallCoordinate =
                    new Coordinate(coordinate.row() + direction[0] / 2, coordinate.col() + direction[1] / 2);
                maze.setCell(wallCoordinate, Cell.Type.PASSAGE);
                generateMaze(maze, newCoordinate);
            }
        }
    }

    private void shuffleDirections(int[][] directions) {
        Random random = new Random();
        for (int i = directions.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }
    }
}
