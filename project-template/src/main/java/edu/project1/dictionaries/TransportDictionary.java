package edu.project1.dictionaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class TransportDictionary implements Dictionary {

    private final Random random = new Random();

    private final static List<String> WORDS = new ArrayList<>() {
        {
            add("motorcycle");
            add("train");
            add("scooter");
            add("hot air balloon");
            add("submarine");
            add("tractor");
        }
    };

    public TransportDictionary() {
    }

    @Override
    public @NotNull String randomWord() {
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
