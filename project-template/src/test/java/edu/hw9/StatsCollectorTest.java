package edu.hw9;

import edu.hw9.task1.Statistics;
import edu.hw9.task1.StatsCollector;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

public class StatsCollectorTest {
    private static final int TEST_COUNT = 10;

    private StatsCollector collector;

    @BeforeEach
    public void setCollector() {
        collector = new StatsCollector();
    }

    @RepeatedTest(10)
    public void collectorShouldCorrectlyCollectInfo() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(12)) {
            for (int i = 0; i < TEST_COUNT; i++) {
                int metric_Number = i;
                executorService.execute(() -> collector.push(
                    "metric_name" + metric_Number,
                    new double[] {0.1, 0.05, 1.4, 5.1, 0.3}
                ));
            }
            executorService.shutdown();
        }
        Assertions.assertThat(collector.getMetricsCount()).isEqualTo(TEST_COUNT);
    }

    @RepeatedTest(10)
    public void collectorShouldCorrectlyGetStats() {
        collector.push(
            "metric_name",
            new double[] {1, 2, 3, 4, 5}
        );
        Map<String, Statistics> stat = collector.stats();
        Assertions.assertThat(stat).isEqualTo(new HashMap<>() {{
            put("metric_name", new Statistics(15, 3.0, 5, 1));
        }});

        collector.push(
            "metric_name2",
            new double[] {0, 0, 0, 0, 0}
        );
        stat = collector.stats();
        Assertions.assertThat(stat).containsExactlyInAnyOrderEntriesOf(new HashMap<>() {{
            put("metric_name", new Statistics(15, 3.0, 5, 1));
            put("metric_name2", new Statistics(0, 0, 0, 0));
        }});
    }
}
