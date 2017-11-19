package org.impstack.util;

/**
 * @author remy
 * @since 10/11/17.
 */
public interface Stack<E> {

    /**
     * Inserts the specified element at the end of the stack.
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     */
    boolean push(E e);

    /**
     * Retrieves and removes the last element of the stack.
     * @return the last element of the stack or {@code null} if this stack is empty
     */
    E pop();

    /**
     * Retrieves, but does not remove the last element of the stack
     * @return the last element of the stack or {@code null} if this stack is empty
     */
    E peek();

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Checks if the collection is empty
     *
     * @return {@code true} if this collection contains no elements or {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Removes all of the elements from this collection.
     */
    void clear();

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).
     * @param e element to be removed from the list
     * @return {@code true} if this list contained the specified element, or {@code false} otherwise
     */
    boolean remove(E e);
}
