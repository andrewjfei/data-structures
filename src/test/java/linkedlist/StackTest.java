package linkedlist;

import dev.andrewjfei.linkedlist.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void push_shouldAddElementToStack() {
        // Given
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // When
        stack.push(1);

        // Then
        assertEquals(1, stack.size());
    }

    @Test
    public void pop_whenStackIsEmpty_shouldReturnNull() {
        // Given
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // When
        Integer popped = stack.pop();

        // Then
        assertNull(popped);
    }

    @Test
    public void pop_whenStackIsNotEmpty_shouldReturnLastElementAdded_andRemoveLastElement() {
        Integer element = 1;

        // Given
        stack.push(element);
        assertEquals(1, stack.size());

        // When
        Integer popped = stack.pop();

        // Then
        assertEquals(element, popped);
        assertNotEquals(element, stack.peek());
    }

    @Test
    public void peek_whenStackIsEmpty_shouldReturnNull() {
        // Given
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // When
        Integer peeked = stack.peek();

        // Then
        assertNull(peeked);
    }

    @Test
    public void peek_whenStackIsNotEmpty_shouldReturnLastElementAdded() {
        Integer element = 1;

        // Given
        stack.push(element);
        assertEquals(1, stack.size());

        // When
        Integer peeked = stack.peek();

        // Then
        assertEquals(element, peeked);
    }

    @Test
    public void isEmpty_whenStackIsEmpty_shouldReturnTrue() {
        // Given
        // When
        assertEquals(0, stack.size());

        // Then
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isEmpty_whenStackIsNotEmpty_shouldReturnFalse() {
        // Given
        // When
        stack.push(1);
        assertEquals(1, stack.size());

        // Then
        assertFalse(stack.isEmpty());
    }

    @Test
    public void size_whenStackIsEmpty_shouldReturnZero() {
        // Given
        // When
        assertTrue(stack.isEmpty());

        // Then
        assertEquals(0, stack.size());
    }

    @Test
    public void size_whenStackIsNotEmpty_shouldReturnNonZero() {
        // Given
        // When
        stack.push(1);
        assertFalse(stack.isEmpty());

        // Then
        assertEquals(1, stack.size());
    }
}
