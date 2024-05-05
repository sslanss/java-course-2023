package edu.hw10.task1;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ReferenceTypesGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    public static String generateString(boolean notNull) {
        if (notNull) {
            return UUID.randomUUID().toString().replace("-", "");
        }
        return random.nextBoolean() ? UUID.randomUUID().toString().replace("-", " ") : null;
    }
}
