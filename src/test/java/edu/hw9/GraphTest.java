package edu.hw9;

import edu.hw9.task3.Graph;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private static Graph graph;

    @BeforeAll
    public static void fillGraph() {
        graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 4);
    }

    @Test
    public void dfsShouldReturnCorrectPath() throws InterruptedException {
        List<Integer> path = graph.dfs(0, 1);
        Assertions.assertThat(path).isEqualTo(new ArrayList<>() {{
            add(0);
            add(1);
        }});

        path = graph.dfs(2, 1);
        Assertions.assertThat(path).isEqualTo(new ArrayList<>() {{
            add(2);
            add(0);
            add(1);
        }});

        path = graph.dfs(3, 1);
        Assertions.assertThat(path).isEqualTo(new ArrayList<>() {{
            add(3);
            add(2);
            add(0);
            add(1);
        }});

        path = graph.dfs(4, 0);
        Assertions.assertThat(path).isEmpty();
    }
}
