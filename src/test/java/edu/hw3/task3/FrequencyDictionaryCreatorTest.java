package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrequencyDictionaryCreatorTest {
    @Test
    public void countFreqsWithEmptyInputList() {
        List<Integer> inputList = new ArrayList<>();
        Map<Integer, Integer> result = FrequencyDictionaryCreator.freqDict(inputList);
        assertThat(result).isEqualTo(new HashMap<>());
    }

    @Test
    public void countFreqsWithNullInputList() {
        ;
        Map<Integer, Integer> result = FrequencyDictionaryCreator.freqDict(null);
        assertThat(result).isEqualTo(new HashMap<>());
    }

    @Test
    public void countFreqsWithInputListOfEqualObjects() {
        List<Integer> inputList = new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
            add(1);
        }};
        Map<Integer, Integer> result = FrequencyDictionaryCreator.freqDict(inputList);
        assertThat(result).isEqualTo(new HashMap<Integer, Integer>() {
            {
                put(1, 4);
            }
        });
    }

    @Test
    public void countFreqsWithInputListOfDifferentObjects() {
        List<String> inputList = new ArrayList<>() {{
            add("a");
            add("b");
            add("c");
            add("d");
        }};
        Map<String, Integer> result = FrequencyDictionaryCreator.freqDict(inputList);
        assertThat(result).isEqualTo(new HashMap<String, Integer>() {
            {
                put("a", 1);
                put("b", 1);
                put("c", 1);
                put("d", 1);
            }
        });
    }

    @Test
    public void countFreqsWithInputListOfMixedObjects() {
        List<String> inputList = new ArrayList<>() {{
            add("that");
            add("that");
            add("that");
            add("this");
            add("those");
            add("those");
            add("0");
            add("это");
        }};
        Map<String, Integer> result = FrequencyDictionaryCreator.freqDict(inputList);
        assertThat(result).isEqualTo(new HashMap<String, Integer>() {
            {
                put("that", 3);
                put("this", 1);
                put("those", 2);
                put("0", 1);
                put("это", 1);
            }
        });
    }
}
