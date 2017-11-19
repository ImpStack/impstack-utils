package org.impstack.util;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link Stack} interface using a backing ArrayList.
 *
 * @author remy
 * @since 10/11/17.
 */
public class ArrayStack<E> implements Stack<E> {

    private final List<E> list;

    public ArrayStack() {
        list = new ArrayList<>();
    }

    @Override
    public boolean push(E e) {
        return list.add(e);
    }

    @Override
    public E pop() {
        return list.isEmpty() ? null : list.remove(list.size() - 1);
    }

    @Override
    public E peek() {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean remove(E e) {
        return list.remove(e);
    }

}
