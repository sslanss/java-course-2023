package edu.hw5.task7_8;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegexUtilsTest {
    @Test
    public void testIsThirdCharAZero() {
        assertThat(RegexUtils.isThirdCharAZero("001")).isFalse();
        assertThat(RegexUtils.isThirdCharAZero("010")).isTrue();
        assertThat(RegexUtils.isThirdCharAZero("1001000")).isTrue();
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("5010")).isFalse();
    }

    @Test
    public void testIsStartsAndEndCharsAreTheSame() {
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("0")).isTrue();
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("00")).isTrue();
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("011")).isFalse();
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("011110")).isTrue();
        assertThat(RegexUtils.isStartsAndEndCharsAreTheSame("050")).isFalse();
    }

    @Test
    public void testHasLengthInRange() {
        assertThat(RegexUtils.hasLengthInRange("0")).isTrue();
        assertThat(RegexUtils.hasLengthInRange("01")).isTrue();
        assertThat(RegexUtils.hasLengthInRange("011")).isTrue();
        assertThat(RegexUtils.hasLengthInRange("0111")).isFalse();
        assertThat(RegexUtils.hasLengthInRange("050")).isFalse();
    }

    @Test
    public void testIsOddLength() {
        assertThat(RegexUtils.isOddLength("000")).isTrue();
        assertThat(RegexUtils.isOddLength("01010")).isTrue();
        assertThat(RegexUtils.isOddLength("01")).isFalse();
        assertThat(RegexUtils.isOddLength("005")).isFalse();
    }

    @Test
    public void testIsEachOddCharIs1() {
        assertThat(RegexUtils.isEachOddCharIs1("1")).isTrue();
        assertThat(RegexUtils.isEachOddCharIs1("101")).isTrue();
        assertThat(RegexUtils.isEachOddCharIs1("110")).isFalse();
        assertThat(RegexUtils.isEachOddCharIs1("1111")).isTrue();
        assertThat(RegexUtils.isEachOddCharIs1("11115")).isFalse();
    }

    @Test
    public void testIsNot11Or111() {
        assertThat(RegexUtils.isNot11Or111("0")).isTrue();
        assertThat(RegexUtils.isNot11Or111("1")).isTrue();
        assertThat(RegexUtils.isNot11Or111("010")).isTrue();
        assertThat(RegexUtils.isNot11Or111("00100")).isTrue();
        assertThat(RegexUtils.isNot11Or111("11")).isFalse();
        assertThat(RegexUtils.isNot11Or111("111")).isFalse();
        assertThat(RegexUtils.isNot11Or111("115")).isFalse();
    }

    @Test
    public void testIsWithOddOrEvenLength() {
        assertThat(RegexUtils.isWithOddOrEvenLength("0")).isTrue();
        assertThat(RegexUtils.isWithOddOrEvenLength("01001")).isTrue();
        assertThat(RegexUtils.isWithOddOrEvenLength("11")).isTrue();
        assertThat(RegexUtils.isWithOddOrEvenLength("01")).isFalse();
        assertThat(RegexUtils.isWithOddOrEvenLength("10101")).isFalse();
        assertThat(RegexUtils.isWithOddOrEvenLength("0015")).isFalse();
    }

    @Test
    public void testIsCount0TheMultipleOf3() {
        assertThat(RegexUtils.isCount0TheMultipleOf3("0")).isFalse();
        assertThat(RegexUtils.isCount0TheMultipleOf3("010")).isFalse();
        assertThat(RegexUtils.isCount0TheMultipleOf3("01010")).isTrue();
        assertThat(RegexUtils.isCount0TheMultipleOf3("000")).isTrue();
        assertThat(RegexUtils.isCount0TheMultipleOf3("0005")).isFalse();
        assertThat(RegexUtils.isCount0TheMultipleOf3("00001")).isFalse();
        assertThat(RegexUtils.isCount0TheMultipleOf3("00100100")).isTrue();
    }
}
