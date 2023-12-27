package edu.hw5.task2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FridayOn13thFinderTest {

    @Test
    public void testGetAllFridaysOn13th() {
        List<LocalDate> result = FridayOn13thFinder.getAllFridaysOn13th(-1);
        Assertions.assertThat(result).isEmpty();

        result = FridayOn13thFinder.getAllFridaysOn13th(1925);
        Assertions.assertThat(result).isEqualTo(new ArrayList<>() {{
            add(LocalDate.of(1925, 2, 13));
            add(LocalDate.of(1925, 3, 13));
            add(LocalDate.of(1925, 11, 13));
        }});

        result = FridayOn13thFinder.getAllFridaysOn13th(2024);
        Assertions.assertThat(result).isEqualTo(new ArrayList<>() {{
            add(LocalDate.of(2024, 9, 13));
            add(LocalDate.of(2024, 12, 13));
        }});
    }

    @Test
    public void testGetNextFridayOn13th() {
        LocalDate result = FridayOn13thFinder.getNextFridayOn13th(LocalDate.of(2024, 9, 12));
        Assertions.assertThat(result).isEqualTo(LocalDate.of(2024, 9, 13));

        result = FridayOn13thFinder.getNextFridayOn13th(LocalDate.of(2024, 9, 14));
        Assertions.assertThat(result).isEqualTo(LocalDate.of(2024, 12, 13));

        result = FridayOn13thFinder.getNextFridayOn13th(LocalDate.of(2024, 12, 13));
        Assertions.assertThat(result).isEqualTo(LocalDate.of(2025, 6, 13));
    }
}
