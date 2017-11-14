package org.impstack.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author remy
 * @since 10/11/17.
 */
public class ArrayStackTest {

    @Test
    public void add() {
        Stack<String> stack = new ArrayStack<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.push("Test"));
        Assert.assertEquals(1, stack.size());
    }

    @Test
    public void pop() {
        Stack<String> stack = new ArrayStack<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.push("Foo"));
        Assert.assertTrue(stack.push("Bar"));
        Assert.assertEquals(2, stack.size());
        Assert.assertEquals("Bar", stack.pop());
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals("Foo", stack.pop());
        Assert.assertEquals(0, stack.size());
        Assert.assertEquals(null, stack.pop());
    }

    @Test
    public void peek() {
        Stack<String> stack = new ArrayStack<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.push("Foo"));
        Assert.assertTrue(stack.push("Bar"));
        Assert.assertEquals(2, stack.size());
        Assert.assertEquals("Bar", stack.peek());
        Assert.assertEquals(2, stack.size());
    }

}
