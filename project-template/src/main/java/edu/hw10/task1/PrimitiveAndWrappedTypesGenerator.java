package edu.hw10.task1;

import java.util.concurrent.ThreadLocalRandom;

public class PrimitiveAndWrappedTypesGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Object generatePrimitive(Annotations range, Class<?> classType) {
        if (classType == byte.class) {
            return generateByte(
                range.min() == null ? Byte.MIN_VALUE : range.min().byteValue(),
                range.max() == null ? Byte.MAX_VALUE : range.max().byteValue()
            );
        }
        if (classType == int.class) {
            return generateInteger(
                range.min() == null ? Integer.MIN_VALUE : range.min().intValue(),
                range.max() == null ? Integer.MAX_VALUE : range.max().intValue()
            );
        }
        if (classType == short.class) {
            return generateShort(
                range.min() == null ? Short.MIN_VALUE : range.min().shortValue(),
                range.max() == null ? Short.MAX_VALUE : range.max().shortValue()
            );
        }
        if (classType == double.class) {
            return generateDouble(
                range.min() == null ? Double.MIN_VALUE : range.min(),
                range.max() == null ? Double.MAX_VALUE : range.max()
            );
        }
        return generateWrappedPrimitive(range, classType);
    }

    public static Object generateWrappedPrimitive(Annotations range, Class<?> classType) {
        if (classType == Byte.class) {
            return range.notNull() ? generateByte(
                range.min() == null ? Byte.MIN_VALUE : range.min().byteValue(),
                range.max() == null ? Byte.MAX_VALUE : range.max().byteValue()
            ) :
                random.nextInt(0, 100) == 5 ? null : generateByte(
                    range.min() == null ? Byte.MIN_VALUE : range.min().byteValue(),
                    range.max() == null ? Byte.MAX_VALUE : range.max().byteValue()
                );
        }
        if (classType == Integer.class) {
            return range.notNull() ? generateInteger(
                range.min() == null ? Integer.MIN_VALUE : range.min().intValue(),
                range.max() == null ? Integer.MAX_VALUE : range.max().intValue()
            ) :
                (random.nextInt(0, 100) == 5 ? null : generateInteger(
                    range.min() == null ? Integer.MIN_VALUE : range.min().intValue(),
                    range.max() == null ? Integer.MAX_VALUE : range.max().intValue()
                ));
        }
        if (classType == Short.class) {
            return range.notNull() ? generateShort(
                range.min() == null ? Short.MIN_VALUE : range.min().shortValue(),
                range.max() == null ? Short.MAX_VALUE : range.max().shortValue()
            ) :
                (random.nextInt(0, 100) == 5 ? null : generateShort(
                    range.min() == null ? Short.MIN_VALUE : range.min().shortValue(),
                    range.max() == null ? Short.MAX_VALUE : range.max().shortValue()
                ));
        }
        if (classType == Double.class) {
            return range.notNull() ? generateDouble(
                range.min() == null ? Double.MIN_VALUE : range.min(),
                range.max() == null ? Double.MAX_VALUE : range.max()
            ) :
                (random.nextInt(0, 100) == 5 ? null : generateDouble(
                    range.min() == null ? Double.MIN_VALUE : range.min(),
                    range.max() == null ? Double.MAX_VALUE : range.max()
                ));
        }
        return null;
    }

    private static byte generateByte(byte min, byte max) {
        return (byte) random.nextInt(min, max);
    }

    private static int generateInteger(int min, int max) {
        return random.nextInt(min, max);
    }

    private static short generateShort(short min, short max) {
        return (short) random.nextInt(min, max);
    }

    private static double generateDouble(double min, double max) {
        return random.nextDouble(min, max);
    }
}
