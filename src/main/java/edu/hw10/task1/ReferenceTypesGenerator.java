package edu.hw10.task1;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ReferenceTypesGenerator {
    private ReferenceTypesGenerator() {

    }

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static String generateString(boolean notNull) {
        if (notNull) {
            return UUID.randomUUID().toString().replace("-", "");
        }
        return RANDOM.nextBoolean() ? UUID.randomUUID().toString().replace("-", " ") : null;
    }
}
