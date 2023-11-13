package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class BracesClusterizer {
    private BracesClusterizer() {
    }

    public static List<String> clusterize(String string) {
        if (string == null) {
            return new ArrayList<>();
        }
        List<String> clusters = new ArrayList<>();
        int countOpen = 0;
        StringBuilder cluster = new StringBuilder();
        int i = 0;
        while (i < string.length()) {
            char brace = string.charAt(i);
            if (brace == '(') {
                countOpen++;
            } else if (countOpen > 0 && brace == ')') {
                countOpen--;
            } else {
                return new ArrayList<>();
            }
            cluster.append(brace);
            if (countOpen == 0) {
                clusters.add(cluster.toString());
                cluster = new StringBuilder();
            }
            i++;
        }
        if (countOpen == 0) {
            return clusters;
        }
        return new ArrayList<>();
    }
}
