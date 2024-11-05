package edu.hw5.task7_8;

import java.util.regex.Pattern;

public final class RegexUtils {
    private RegexUtils() {

    }

    //задание 7
    //содержит не менее 3 символов, причем третий символ равен 0
    private static final Pattern MORE_THAT_THIRD = Pattern.compile("^[01]{2}0[01]*$");

    //начинается и заканчивается одним и тем же символом |^.*$
    private static final Pattern START_AND_END_CHARS_ARE_THE_SAME = Pattern.compile("^([0|1])([0|1]*\\1$)?$");

    //длина не менее 1 и не более 3
    private static final Pattern LENGTH_IN_RANGE = Pattern.compile("^[0|1]{1,3}$");

    //задание 8
    //нечетной длины
    private static final Pattern ODD_LENGTH = Pattern.compile("^[0|1]([0|1]{2})*$");

    //каждый нечетный символ равен 1
    private static final Pattern EACH_ODD_CHAR_IS_1 = Pattern.compile("^1([01]*1)*$");

    //любая строка, кроме 11 или 111
    private static final Pattern NOT_11_OR_111 = Pattern.compile("^(?!11$|111$)[01]+$");

    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    private static final Pattern WITH_ODD_OR_EVEN_LENGTH = Pattern.compile("^(0([0|1]{2})*$)|^(1[0|1]([0|1]{2})*$)");

    //количество 0 кратно 3
    private static final Pattern COUNT_OF_0_IS_MULTIPLE_OF_3 = Pattern.compile("^(1*01*01*01*)*$");

    public static boolean isThirdCharAZero(String str) {
        return (MORE_THAT_THIRD.matcher(str).find());
    }

    public static boolean isStartsAndEndCharsAreTheSame(String str) {
        return (START_AND_END_CHARS_ARE_THE_SAME.matcher(str).find());
    }

    public static boolean hasLengthInRange(String str) {
        return (LENGTH_IN_RANGE.matcher(str).find());
    }

    public static boolean isOddLength(String str) {
        return (ODD_LENGTH.matcher(str).find());
    }

    public static boolean isEachOddCharIs1(String str) {
        return (EACH_ODD_CHAR_IS_1.matcher(str).find());
    }

    public static boolean isNot11Or111(String str) {
        return (NOT_11_OR_111.matcher(str).find());
    }

    public static boolean isWithOddOrEvenLength(String str) {
        return (WITH_ODD_OR_EVEN_LENGTH.matcher(str).find());
    }

    public static boolean isCount0TheMultipleOf3(String str) {
        return (COUNT_OF_0_IS_MULTIPLE_OF_3.matcher(str).find());
    }
}
