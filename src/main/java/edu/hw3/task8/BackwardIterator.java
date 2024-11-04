package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final ListIterator<T> iterator;

    public BackwardIterator(List<T> list) {
        iterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.previous();
    }
}
