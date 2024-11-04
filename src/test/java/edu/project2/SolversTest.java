package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class SolversTest {
    private final Random random = new Random();
    private Maze maze;

    @BeforeEach
    public void setUpMaze() {
        maze = new Maze(3, 3);
    }

    private static Arguments[] solvers() {
        return new Arguments[] {
            Arguments.of(new BFSSolver()),
            Arguments.of(new DFSSolver())
        };
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void shouldReturnPathInCaseAllCellsArePassages(Solver solver) {
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                maze.setCell(new Coordinate(i, j), Cell.Type.PASSAGE);
            }
        }
        Coordinate start = new Coordinate(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));
        Coordinate end = new Coordinate(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));

        if (!start.equals(end)) {
            assertThat(solver.solve(maze, start, end)).isNotEmpty();
        }
    }

    @ParameterizedTest
    @MethodSource("solvers")
    public void shouldReturnEmptyPathInCaseAllCellsAreWalls(Solver solver) {
        Coordinate start = new Coordinate(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));
        Coordinate end = new Coordinate(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));

        assertThat(solver.solve(maze, start, end)).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("solvers")
    public void shouldReturnEmptyPathInCaseItDoesNotExist(Solver solver) {
        maze.setCell(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(2, 0), Cell.Type.PASSAGE);

        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(0, 2));

        assertThat(resultPath).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("solvers")
    public void shouldReturnPathInCaseStartAndEndCellsAreTheSame(Solver solver) {
        maze.setCell(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 2), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 2), Cell.Type.PASSAGE);

        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(0, 0));
        List<Coordinate> expectedPath = new ArrayList<>() {{
            add(new Coordinate(0, 0));
        }};

        assertThat(resultPath).isEqualTo(expectedPath);
    }

    @ParameterizedTest
    @MethodSource("solvers")
    public void shouldReturnPathInCaseStartAndEndCellsAreNeighbours(Solver solver) {
        maze.setCell(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 2), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 2), Cell.Type.PASSAGE);

        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(0, 1));
        List<Coordinate> expectedPath = new ArrayList<>() {{
            add(new Coordinate(0, 0));
            add(new Coordinate(0, 1));
        }};

        assertThat(resultPath).isEqualTo(expectedPath);
    }

    @ParameterizedTest
    @MethodSource("solvers")
    public void shouldReturnPathInCaseItIsStraight(Solver solver) {
        maze.setCell(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(0, 2), Cell.Type.PASSAGE);

        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(0, 2));
        List<Coordinate> expectedPath = new ArrayList<>() {{
            add(new Coordinate(0, 0));
            add(new Coordinate(0, 1));
            add(new Coordinate(0, 2));
        }};
        assertThat(resultPath).isEqualTo(expectedPath);
    }

    @Test
    public void bfsSolverShouldReturnPathInCaseItShouldBeBacktracked() {
        maze = new Maze(4, 4);
        maze.setCell(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(2, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(3, 0), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(3, 1), Cell.Type.PASSAGE);
        maze.setCell(new Coordinate(3, 2), Cell.Type.PASSAGE);

        Solver solver = new BFSSolver();

        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(1, 2));
        List<Coordinate> expectedPath = new ArrayList<>() {{
            add(new Coordinate(0, 0));
            add(new Coordinate(1, 0));
            add(new Coordinate(1, 1));
            add(new Coordinate(1, 2));
        }};
        assertThat(resultPath).isEqualTo(expectedPath);

        resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(3, 2));

        expectedPath = new ArrayList<>() {{
            add(new Coordinate(0, 0));
            add(new Coordinate(1, 0));
            add(new Coordinate(2, 0));
            add(new Coordinate(3, 0));
            add(new Coordinate(3, 1));
            add(new Coordinate(3, 2));
        }};

        assertThat(resultPath).isEqualTo(expectedPath);
    }
}
