package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record MyRecord(@Min(value = Byte.MIN_VALUE) @Max(value = Byte.MAX_VALUE) byte byteField,
                       @Min(value = Short.MIN_VALUE) @Max(value = Short.MAX_VALUE) short shortField,
                       @Min(value = Integer.MIN_VALUE) @Max(value = Integer.MAX_VALUE) int intField,
                       @Min(value = Double.MIN_VALUE) @Max(value = Double.MAX_VALUE) double doubleField,
                       @NotNull String stringField) {
}
