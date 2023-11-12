package edu.hw3.task8;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackwardIteratorTest {

    @Test
    public void backwardIteratorWithLists(){
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> it = new BackwardIterator<>(list);
        while (it.hasNext()){
            result.add(it.next());
        }
        assertThat(result).isEqualTo(new ArrayList<>(){{
            add(3);
            add(2);
            add(1);
        }});

        list = new LinkedList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};
        result = new LinkedList<>();
        it = new BackwardIterator<>(list);
        while (it.hasNext()){
            result.add(it.next());
        }
        assertThat(result).isEqualTo(new LinkedList<>(){{
            add(3);
            add(2);
            add(1);
        }});
    }
}
