package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BackwardIterator2<T> implements Iterator<T> {

    private final Stack<T> stack;

    public BackwardIterator2(Collection<T> collection) {
        stack = new Stack<>();
        stack.addAll(collection);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (hasNext()) {
            return stack.pop();
        } else {
            throw new NoSuchElementException();
        }
    }
}
