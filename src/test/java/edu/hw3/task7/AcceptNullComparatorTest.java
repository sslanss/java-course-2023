package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AcceptNullComparatorTest {
    @Test
    public void acceptNull() {
        TreeMap<String, String> tree = new TreeMap<>(new AcceptNullComparator<>());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }
}
