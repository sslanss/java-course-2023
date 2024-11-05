package edu.hw5.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubstringCheckerTest {
    @Test
    public void testSubstringFound() {
        String string = "abc abc";
        String substring = "abc";

        boolean result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isTrue();
    }

    @Test
    public void testSubstringNotFound() {
        String string = "abcabcabc";
        String substring = "df";

        boolean result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isFalse();

        string = "abc";
        substring = "abcabc";

        result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isFalse();
    }

    @Test
    public void testEmptySubstring() {
        String string = "aba cabc";
        String substring = "";

        boolean result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isFalse();
    }

    @Test
    public void testEmptyString() {
        String string = "";
        String substring = "aba cabc";

        boolean result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isFalse();
    }

    @Test
    public void testEqualsStringAndSubstring() {
        String string = "aba cabc";
        String substring = "aba cabc";

        boolean result = SubstringChecker.isSubstring(string, substring);

        assertThat(result).isTrue();
    }
}
