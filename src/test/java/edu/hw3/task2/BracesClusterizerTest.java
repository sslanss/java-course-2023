package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BracesClusterizerTest {

    @Test
    public void clusterizeWithEmptyInputString() {
        String inputString = "";
        List<String> result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clusterizeWithNullInputString() {
        List<String> result = BracesClusterizer.clusterize(null);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clusterizeWithIncorrectInputString() {
        String inputString = "(((a)()))";
        List<String> result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "((()()))a";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clusterizeWithUnclusterizedInputString() {
        String inputString = "((()())))";
        List<String> result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "(((()()))";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "))((";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "))))";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
        inputString = "(((()(()))";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void clusterizeWithClusterizedInputString() {
        String inputString = "()()()";
        List<String> result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("()");
            add("()");
            add("()");
        }});
        inputString = "(()()())";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("(()()())");
        }});
        inputString = "((()))(())()()(()())";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("((()))");
            add("(())");
            add("()");
            add("()");
            add("(()())");
        }});
        inputString = "((())())(()(()()))";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("((())())");
            add("(()(()()))");
        }});
        inputString = "(((())())(()(()())))";
        result = BracesClusterizer.clusterize(inputString);
        assertThat(result).isEqualTo(new ArrayList<>() {{
            add("(((())())(()(()())))");
        }});
    }
}
