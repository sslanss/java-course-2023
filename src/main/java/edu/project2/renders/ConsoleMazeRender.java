package edu.project2.renders;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ConsoleMazeRender implements Renderer {
    @Override
    public String renderMaze(Maze maze) {
        StringBuilder renderedStr = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                renderedStr.append(grid[i][j].type().equals(Cell.Type.PASSAGE) ? "0" : "█");
            }
            renderedStr.append('\n');
        }
        return renderedStr.toString();
    }

    @Override
    public String renderPath(Maze maze, @NotNull List<Coordinate> path) {
        StringBuilder renderedStr = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (path.contains(new Coordinate(i, j))) {
                    renderedStr.append("#");
                } else {
                    renderedStr.append(grid[i][j].type().equals(Cell.Type.PASSAGE) ? "0" : "█");
                }
            }
            renderedStr.append('\n');
        }
        return renderedStr.toString();
    }
}
