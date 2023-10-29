package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> list;

    private int position;

    public BackwardIterator(List<T> list) {
        this.list = list;
        position = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return list.get(position--);
        } else {
            throw new NoSuchElementException();
        }
    }

}
