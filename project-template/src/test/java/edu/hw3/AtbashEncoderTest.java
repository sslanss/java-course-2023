package edu.hw3;

import edu.hw3.task1.AtbashEncoder;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashEncoderTest {
    @Test
    public void encodeAtbashWithEmptyInputString() {
        String inputString = "";
        String result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("");
    }
    @Test
    public void encodeAtbashWithNullInputString() {
        String result = AtbashEncoder.atbash(null);
        assertThat(result).isEqualTo("");
    }
    @Test
    public void encodeAtbashWithNotLatinInputString() {
        String inputString = "Ааааа";
        String result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("Ааааа");
        inputString = "    ";
        result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("    ");
    }

    @Test
    public void encodeAtbashWithLowerCaseInputString() {
        String inputString = "asdf";
        String result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("zhwu");
    }

    @Test
    public void encodeAtbashWithUpperCaseInputString() {
        String inputString = "ASDF";
        String result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("ZHWU");
    }

    @Test
    public void encodeAtbashWithMixedInputString() {
        String inputString = "Hello world!";
        String result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("Svool dliow!");
        inputString = "Good programmers write code that humans can understand. ― Martin Fowler";
        result = AtbashEncoder.atbash(inputString);
        assertThat(result).isEqualTo("Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
}
