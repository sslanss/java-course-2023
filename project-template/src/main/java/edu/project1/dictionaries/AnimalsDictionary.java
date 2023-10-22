package edu.project1.dictionaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class AnimalsDictionary implements Dictionary {

    private final Random random = new Random();

    private final static List<String> WORDS = new ArrayList<>() {
        {
            add("lizard");
            add("leopard");
            add("peacock");
            add("hyena");
            add("hornet");
            add("water beetle");
        }
    };

    public AnimalsDictionary() {
    }

    @Override
    public @NotNull String randomWord() {
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
