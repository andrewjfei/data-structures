package tree;

import dev.andrewjfei.exceptions.EmptyTreeException;
import dev.andrewjfei.tree.BinaryHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryHeapTest {
    private BinaryHeap<Integer> heap;

    @BeforeEach
    public void setUp() {
        heap = new BinaryHeap<>();
    }

    @Test
    public void insert_whenHeapIsEmpty_shouldAddElementToHeap() {
        // Given
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        // When
        heap.insert(1);

        // Then
        assertEquals(1, heap.size());
    }

    @Test
    public void insert_whenHeapIsNotEmpty_shouldAddElementToHeap() {
        // Given
        heap.insert(1);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());

        // When
        heap.insert(10);

        // Then
        assertEquals(2, heap.size());
    }

    @Test
    public void insert_whenHeapIsNotEmpty_andInsertGreatestElement_shouldSwimElementToHeapRoot() {
        Integer element = 10;

        // Given
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        assertEquals(5, heap.size());

        // When
        heap.insert(element);

        // Then
        assertEquals(6, heap.size());
        assertEquals(element, heap.peek());
    }

    @Test
    public void poll_whenHeapIsEmpty_shouldThrowException() {
        // Given
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        // When
        // Then
        assertThrows(EmptyTreeException.class, () -> heap.poll());
    }

    @Test
    public void poll_whenHeapIsNotEmpty_shouldReturnRootElement_andRemoveRootElement() {
        Integer element = 10;

        // Given
        heap.insert(element);
        heap.insert(3);
        heap.insert(9);
        heap.insert(2);
        heap.insert(1);
        heap.insert(8);
        heap.insert(7);
        assertFalse(heap.isEmpty());
        assertEquals(7, heap.size());

        // When
        Integer polled = heap.poll();

        // Then
        assertEquals(element, polled);
    }

    @Test
    public void peek_whenHeapIsEmpty_shouldReturnNull() {
        // Given
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        // When
        Integer peeked = heap.peek();

        // Then
        assertNull(peeked);
    }

    @Test
    public void peek_whenHeapIsNotEmpty_shouldReturnRootElement() {
        int element = 1;

        // Given
        heap.insert(element);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());

        // When
        int peeked = heap.peek();

        // Then
        assertEquals(element, peeked);
    }

    @Test
    public void contains_whenElementIsNotInHeap_shouldReturnFalse() {
        Integer element = 1;

        // Given
        heap.insert(10);
        assertEquals(1, heap.size());

        // When
        // Then
        assertFalse(heap.contains(element));
    }

    @Test
    public void contains_whenElementIsInHeap_shouldReturnTrue() {
        Integer element = 1;

        // Given
        heap.insert(element);
        assertEquals(1, heap.size());

        // When
        // Then
        assertTrue(heap.contains(element));
    }

    @Test
    public void size_whenHeapIsEmpty_shouldReturnZero() {
        // Given
        // When
        assertTrue(heap.isEmpty());

        // Then
        assertEquals(0, heap.size());
    }

    @Test
    public void size_whenHeapIsNotEmpty_shouldReturnNonZero() {
        // Given
        // When
        heap.insert(1);
        assertFalse(heap.isEmpty());

        // Then
        assertEquals(1, heap.size());
    }

    @Test
    public void isEmpty_whenHeapIsEmpty_shouldReturnTrue() {
        // Given
        // When
        assertEquals(0, heap.size());

        // Then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void isEmpty_whenHeapIsNotEmpty_shouldReturnFalse() {
        // Given
        // When
        heap.insert(1);
        assertEquals(1, heap.size());

        // Then
        assertFalse(heap.isEmpty());
    }
}
