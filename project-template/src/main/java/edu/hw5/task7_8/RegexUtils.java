package edu.hw5.task7_8;

import java.util.regex.Pattern;

public final class RegexUtils {

    //задание 7
    //содержит не менее 3 символов, причем третий символ равен 0
    private static final Pattern moreThatThird = Pattern.compile("^[01]{2}0[01]*$");

    //начинается и заканчивается одним и тем же символом |^.*$
    private static final Pattern startAndEndCharsAreTheSame = Pattern.compile("^([0|1])([0|1]*\\1$)?$");

    //длина не менее 1 и не более 3
    private static final Pattern LengthInRange = Pattern.compile("^[0|1]{1,3}$");

    //задание 8
    //нечетной длины
    private static final Pattern oddLength = Pattern.compile("^[0|1]([0|1]{2})*$");

    //каждый нечетный символ равен 1
    private static final Pattern eachOddCharIs1 = Pattern.compile("^1([0|1]1)*$");

    //любая строка, кроме 11 или 111
    private static final Pattern not11Or111 = Pattern.compile("^(?!11$|111$)[01]+$");

    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    private static final Pattern withOddOrEvenLength = Pattern.compile("^(0([0|1]{2})*$)|^(1[0|1]([0|1]{2})*$)");

    //количество 0 кратно 3
    private static final Pattern countOf0IsMultipleOf3 = Pattern.compile("^(1*01*01*01*)*$");

    public boolean isThirdCharAZero(String str) {
        return (moreThatThird.matcher(str).find());
    }

    public boolean isStartsAndEndCharsAreTheSame(String str) {
        return (startAndEndCharsAreTheSame.matcher(str).find());
    }

    public boolean hasLengthInRange(String str) {
        return (LengthInRange.matcher(str).find());
    }
    public boolean isOddLength(String str) {
        return (oddLength.matcher(str).find());
    }
    public boolean isEachOddCharIs1(String str) {
        return (eachOddCharIs1.matcher(str).find());
    }
    public boolean isNot11Or111(String str) {
        return (not11Or111.matcher(str).find());
    }
    public boolean isWithOddOrEvenLength(String str) {
        return (withOddOrEvenLength.matcher(str).find());
    }
    public boolean isCount0TheMultipleOf3(String str) {
        return (countOf0IsMultipleOf3.matcher(str).find());
    }
}
