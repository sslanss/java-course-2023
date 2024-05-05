package edu.hw9.task1;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ConcurrentMap<String, List<Double>> collector;


    public StatsCollector() {
        collector = new ConcurrentHashMap<>();
    }

    public int getMetricsCount() {
        return collector.size();
    }

    public void push(String metricName, double[] data) {
        List<Double> values = new ArrayList<>(data.length);
        for (double value : data) {
            values.add(value);
        }
        collector.put(metricName, values);
    }

    private Statistics getStatistics(String metricName) {
        List<Double> metric = collector.get(metricName);

        if (metric.isEmpty()) {
            return new Statistics(0, 0, 0, 0);
        }

        DoubleSummaryStatistics stats = metric.stream()
            .mapToDouble(Double::doubleValue)
            .summaryStatistics();

        return new Statistics(stats.getSum(), stats.getAverage(), stats.getMax(), stats.getMin());
    }

    public Map<String, Statistics> stats() {
        Map<String, Statistics> statisticsMap = new HashMap<>();
        try(ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors())) {
            for (var metricName : collector.keySet()) {
                executorService.execute(() -> {
                    Statistics statistics = getStatistics(metricName);
                    synchronized (statisticsMap) {
                        statisticsMap.put(metricName, statistics);
                    }
                });
            }
            executorService.shutdown();
        }
        return statisticsMap;
    }

}
