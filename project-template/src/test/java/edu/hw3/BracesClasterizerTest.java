package edu.hw3;

import edu.hw3.task2.BracesClasterizer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BracesClasterizerTest {

    @Test
    public void clasterizeWithEmptyInputString() {
        String inputString = "";
        List<String> result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clasterizeWithNullInputString() {
        List<String> result = BracesClasterizer.clusterize(null);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clasterizeWithIncorrectInputString() {
        String inputString = "(((a)()))";
        List<String> result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "((()()))a";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clasterizeWithUnclasterizedInputString() {
        String inputString = "((()())))";
        List<String> result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "(((()()))";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "((((";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "))))";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "(((()(()))";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clasterizeWithClasterizedInputString() {
        String inputString = "()()()";
        List<String> result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("()");
            add("()");
            add("()");
        }});
        inputString = "(()()())";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("(()()())");
        }});
        inputString = "((()))(())()()(()())";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("((()))");
            add("(())");
            add("()");
            add("()");
            add("(()())");
        }});
        inputString = "((())())(()(()()))";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("((())())");
            add("(()(()()))");
        }});
        inputString = "(((())())(()(()())))";
        result = BracesClasterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("(((())())(()(()())))");
        }});
    }
}
