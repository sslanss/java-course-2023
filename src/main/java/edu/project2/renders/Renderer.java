package edu.project2.renders;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public interface Renderer {
    String renderMaze(Maze maze);

    String renderPath(Maze maze, List<Coordinate> path);
}
