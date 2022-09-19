package tree;

import dev.andrewjfei.exceptions.EmptyTreeException;
import dev.andrewjfei.exceptions.NodeAlreadyExistsException;
import dev.andrewjfei.tree.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void add_whenBinarySearchTreeIsEmpty_shouldAddElementToTree() {
        // Given
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());

        // When
        bst.add(50);

        // Then
        assertEquals(1, bst.size());
    }

    @Test
    public void add_whenBinarySearchTreeIsNotEmpty_shouldAddElementToTree() {
        Integer element = 25;

        // Given
        bst.add(50);
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());

        // When
        bst.add(element);

        // Then
        assertEquals(2, bst.size());
        assertTrue(bst.contains(element));
    }

    @Test
    public void add_whenBinarySearchTreeIsNotEmpty_andElementAlreadyExists_shouldThrowException() {
        Integer element = 25;

        // Given
        bst.add(element);
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());

        // When
        // Then
        assertThrows(NodeAlreadyExistsException.class, () -> bst.add(element));
    }

    @Test
    public void remove_whenBinarySearchTreeIsEmpty_shouldThrowException() {
        // Given
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());

        // When
        // Then
        assertThrows(EmptyTreeException.class, () -> bst.remove(50));
    }

    @Test
    public void remove_whenBinarySearchTreeIsNotEmpty_shouldReturnElement_andRemoveElementFromTree() {
        Integer element = 25;

        // Given
        bst.add(50);
        bst.add(element);
        bst.add(21);
        bst.add(27);
        bst.add(24);
        bst.add(23);
        bst.add(18);
        bst.add(19);
        bst.add(20);
        bst.add(15);
        assertFalse(bst.isEmpty());
        assertEquals(10, bst.size());

        // When
        Integer removed = bst.remove(element);

        System.out.println(bst);

        // Then
        assertEquals(9, bst.size());
        assertFalse(bst.contains(element));
        assertEquals(element, removed);
    }

    @Test
    public void peek_whenBinarySearchTreeIsEmpty_shouldReturnNull() {
        Integer element = 25;

        // Given
        bst.add(50);
        bst.add(element);
        assertFalse(bst.isEmpty());
        assertEquals(2, bst.size());

        // When
        bst.remove(element);

        // Then
        assertEquals(1, bst.size());
        assertFalse(bst.contains(element));
    }

    @Test
    public void peek_whenBinarySearchTreeIsNotEmpty_shouldReturnRootElement() {
        Integer element = 50;

        // Given
        bst.add(element);
        bst.add(25);
        bst.add(75);
        assertFalse(bst.isEmpty());
        assertEquals(3, bst.size());

        // When
        Integer peeked = bst.peek();

        // Then
        assertEquals(3, bst.size());
        assertEquals(element, peeked);
    }

    @Test
    public void test() {
        Integer element = 25;

        // Given
        bst.add(50);

        // When
        bst.add(element);
        bst.add(20);

        System.out.println(bst);
        // Then
    }

    @Test
    public void contains_whenElementIsNotInHeap_shouldReturnFalse() {
        Integer element = 1;

        // Given
        bst.add(10);
        assertEquals(1, bst.size());

        // When
        // Then
        assertFalse(bst.contains(element));
    }

    @Test
    public void contains_whenElementIsInHeap_shouldReturnTrue() {
        Integer element = 1;

        // Given
        bst.add(element);
        assertEquals(1, bst.size());

        // When
        // Then
        assertTrue(bst.contains(element));
    }

    @Test
    public void size_whenBinarySearchTreeIsEmpty_shouldReturnZero() {
        // Given
        // When
        assertTrue(bst.isEmpty());

        // Then
        assertEquals(0, bst.size());
    }

    @Test
    public void size_whenBinarySearchTreeIsNotEmpty_shouldReturnNonZero() {
        // Given
        // When
        bst.add(1);
        assertFalse(bst.isEmpty());

        // Then
        assertEquals(1, bst.size());
    }

    @Test
    public void isEmpty_whenBinarySearchTreeIsEmpty_shouldReturnTrue() {
        // Given
        // When
        assertEquals(0, bst.size());

        // Then
        assertTrue(bst.isEmpty());
    }

    @Test
    public void isEmpty_whenBinarySearchTreeIsNotEmpty_shouldReturnFalse() {
        // Given
        // When
        bst.add(1);
        assertEquals(1, bst.size());

        // Then
        assertFalse(bst.isEmpty());
    }
}
