package edu.project1.dictionaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class FillingsDictionary implements Dictionary {

    private final Random random = new Random();

    private final static List<String> WORDS = new ArrayList<>() {
        {
            add("tenderness");
            add("serenity");
            add("disgust");
            add("depression");
            add("longing");
            add("confidence");
            add("respect");
        }
    };

    public FillingsDictionary() {
    }

    @Override
    public @NotNull String randomWord() {
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
