package edu.project2;

import edu.project2.generators.DFSGenerator;
import edu.project2.generators.Generator;
import edu.project2.generators.PrimGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class GeneratorsTest {
    private static Arguments[] generators() {
        return new Arguments[] {
            Arguments.of(new DFSGenerator()),
            Arguments.of(new PrimGenerator())
        };
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void shouldGenerateMazeWithInputSize(Generator generator) {
        Maze maze = generator.generate(5, 5);

        assertThat(maze).isNotNull();
        assertThat(maze.getHeight()).isEqualTo(5);
        assertThat(maze.getWidth()).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void shouldNotGenerateMazeWithOnlyWalls(Generator generator) {
        Maze maze = generator.generate(5, 5);

        int countPassages = 0;
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].type().equals(Cell.Type.PASSAGE)) {
                    ++countPassages;
                }
                ;
            }
        }

        assertThat(countPassages).isNotEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void shouldNotGenerateMazeWithOnlyPassages(Generator generator) {
        Maze maze = generator.generate(5, 5);

        int countWalls = 0;
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].type().equals(Cell.Type.WALL)) {
                    ++countWalls;
                }
                ;
            }
        }

        assertThat(countWalls).isNotEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void whenHeightOrWidthIsNonPositiveShouldThrowException(Generator generator) {
        assertThatIllegalArgumentException().isThrownBy(() -> generator.generate(-5, 7));
        assertThatIllegalArgumentException().isThrownBy(() -> generator.generate(5, -5));
        assertThatIllegalArgumentException().isThrownBy(() -> generator.generate(-5, -5));
    }
}
