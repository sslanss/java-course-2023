package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class BracesClasterizer {
    private BracesClasterizer() {
    }

    public static List<String> clusterize(String string) {
        if (string == null) {
            return new ArrayList<>();
        } else {
            List<String> clusters = new ArrayList<>();
            int countOpen = 0;
            StringBuilder cluster = new StringBuilder();
            int i = 0;
            boolean isStringCorrect = true;
            while (i < string.length() && isStringCorrect) {
                char brace = string.charAt(i);
                if (brace == '(') {
                    countOpen++;
                } else if (brace == ')') {
                    countOpen--;
                } else {
                    isStringCorrect = false;
                }
                cluster.append(brace);
                if (countOpen == 0) {
                    clusters.add(cluster.toString());
                    cluster = new StringBuilder();
                }
                i++;
            }
            if (countOpen == 0 && isStringCorrect) {
                return clusters;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
