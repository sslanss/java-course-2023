package edu.project1.dictionaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class CountriesDictionary implements Dictionary {

    private final Random random = new Random();

    private final static List<String> WORDS = new ArrayList<>() {
        {
            add("china");
            add("luxembourg");
            add("indonesia");
            add("gibraltar");
            add("singapore");
        }
    };

    public CountriesDictionary() {
    }

    @Override
    public @NotNull String randomWord() {
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
