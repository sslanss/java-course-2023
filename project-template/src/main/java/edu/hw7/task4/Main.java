package edu.hw7.task4;

import java.util.ArrayList;

public class Main {

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        MonteCarloEfficiencyAnalyzer analyzer = new MonteCarloEfficiencyAnalyzer();
        analyzer.countAverageExecutionTimeInMillis(new ArrayList<>() {{
            add(1);
            add(2);
            add(4);
            add(8);
            add(12);
        }});
        analyzer.countComputationErrors(new ArrayList<>() {{
            add(10000000L);
            add(100000000L);
            add(1000000000L);
        }});
    }
}
