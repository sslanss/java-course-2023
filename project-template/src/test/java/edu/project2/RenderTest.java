package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renders.Renderer;
import edu.project2.renders.ConsoleMazeRender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class RenderTest {
    private final static Maze MAZE = new Maze(2, 2);

    @BeforeAll
    public static void setUpMaze() {
        for (int i = 0; i < MAZE.getHeight(); ++i) {
            for (int j = 0; j < MAZE.getWidth(); ++j) {
                if ((i == 1 && j == 0) || (i == 1 && j == 1)) {
                    continue;
                }
                MAZE.setCell(new Coordinate(i, j), Cell.Type.PASSAGE);
            }
        }
    }
    @Test
    public void testRender() {
        Renderer renderer = new ConsoleMazeRender();

        String result = renderer.renderMaze(MAZE);
        String expected = "00\n██\n";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRenderWithPath() {
        Renderer renderer = new ConsoleMazeRender();

        List<Coordinate> path = new ArrayList<>(){{
            add(new Coordinate(0, 0));
            add(new Coordinate(0, 1));
        }};

        String result = renderer.renderPath(MAZE, path);
        String expected = "##\n██\n";

        assertThat(result).isEqualTo(expected);
    }
}
