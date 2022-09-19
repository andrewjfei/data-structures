package linkedlist;

import dev.andrewjfei.exceptions.EmptyListException;
import dev.andrewjfei.linkedlist.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void enqueue_whenQueueIsEmpty_shouldAddElementToQueue() {
        // Given
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        // When
        queue.enqueue(1);

        // Then
        assertEquals(1, queue.size());
    }

    @Test
    public void enqueue_whenQueueIsNotEmpty_shouldAddElementToQueue() {
        // Given
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        // When
        queue.enqueue(10);

        // Then
        assertEquals(2, queue.size());
    }

    @Test
    public void dequeue_whenQueueIsEmpty_shouldThrowException() {
        // Given
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        // When
        // Then
        assertThrows(EmptyListException.class, () -> queue.dequeue());
    }

    @Test
    public void dequeue_whenQueueIsNotEmpty_shouldReturnFirstElementAdded_andRemoveFirstElement() {
        Integer element = 1;

        // Given
        queue.enqueue(element);
        queue.enqueue(10);
        assertEquals(2, queue.size());

        // When
        Integer dequeued = queue.dequeue();

        // Then
        assertEquals(element, dequeued);
        assertEquals(1, queue.size());
        assertNotEquals(element, queue.peek());
    }

    @Test
    public void peek_whenQueueIsEmpty_shouldReturnNull() {
        // Given
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        // When
        Integer peeked = queue.peek();

        // Then
        assertNull(peeked);
    }

    @Test
    public void peek_whenQueueIsNotEmpty_shouldReturnFirstElementAdded() {
        Integer element = 1;

        // Given
        queue.enqueue(element);
        queue.enqueue(10);
        assertEquals(2, queue.size());

        // When
        Integer peeked = queue.peek();

        // Then
        assertEquals(element, peeked);
    }

    @Test
    public void contains_whenElementIsNotInQueue_shouldReturnFalse() {
        Integer element = 1;

        // Given
        queue.enqueue(10);
        assertEquals(1, queue.size());

        // When
        // Then
        assertFalse(queue.contains(element));
    }

    @Test
    public void contains_whenElementIsInQueue_shouldReturnTrue() {
        Integer element = 1;

        // Given
        queue.enqueue(element);
        assertEquals(1, queue.size());

        // When
        // Then
        assertTrue(queue.contains(element));
    }

    @Test
    public void size_whenQueueIsEmpty_shouldReturnZero() {
        // Given
        // When
        assertTrue(queue.isEmpty());

        // Then
        assertEquals(0, queue.size());
    }

    @Test
    public void size_whenQueueIsNotEmpty_shouldReturnNonZero() {
        // Given
        // When
        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        // Then
        assertEquals(1, queue.size());
    }

    @Test
    public void isEmpty_whenStackIsEmpty_shouldReturnTrue() {
        // Given
        // When
        assertEquals(0, queue.size());

        // Then
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isEmpty_whenStackIsNotEmpty_shouldReturnFalse() {
        // Given
        // When
        queue.enqueue(1);
        assertEquals(1, queue.size());

        // Then
        assertFalse(queue.isEmpty());
    }
}
