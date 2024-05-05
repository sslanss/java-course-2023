package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FrequencyDictionaryCreator {

    private FrequencyDictionaryCreator() {

    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> freqMap = new HashMap<>();
        if (list != null) {
            for (T item : list) {
                freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
            }
        }
        return freqMap;
    }
}
