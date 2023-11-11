package edu.project2.solvers;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public abstract class Solver {
    protected int[][] DIRECTIONS
        = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    boolean[][] visited;

    public abstract List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    protected void setVisited(Coordinate coordinate) {
        this.visited[coordinate.row()][coordinate.col()] = true;
    }

    protected boolean isExplored(Coordinate coordinate) {
        return visited[coordinate.row()][coordinate.col()];
    }
}
