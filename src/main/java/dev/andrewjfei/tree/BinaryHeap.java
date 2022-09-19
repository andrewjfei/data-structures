package dev.andrewjfei.tree;

import dev.andrewjfei.exceptions.EmptyTreeException;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@code Heap} is a tree based data structure where the parent node always has greater "priority" compared to the
 * children nodes, for all nodes within the tree. This is known as the <b>heap invariant</b>.
 *
 * <p> There are two types of {@code Heap} data structures, <b>Min Heap</b> where smaller values have greater priority
 * and <b>Max Heap</b> where larger values have greater priority.
 *
 * A node in a {@code Heap} can also have an arbitrary number of children. However, restrictions can be made for the
 * data structure. For example, we can place a restriction where a node at most can only have two children, this is
 * called a <b>Binary Heap</b>.
 *
 * @see Comparable
 *
 * @author andrewjfei
 *
 * @param <T> the data type of the list which must be {@code Comparable}.
 */
public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;
    private int maxSize;

    public BinaryHeap() {
        heap = new ArrayList<>();
        size = 0;
        maxSize = 1;
    }

    /**
     * The {@code insert} method adds an element into the heap.
     *
     * <p> This operation takes {@code O(log(n))} time as new element is first added the next available position in the
     * heap. The {@code swim} method is then recursively called until the <b>heap invariant</b> is met.
     *
     * @param t the element which is being added to the heap.
     */
    public void insert(T t) {
        if (size >= maxSize) {
            maxSize++;
        }

        heap.add(t);
        size++;
        swim(size - 1);
    }

    /**
     * The {@code poll} method removes the element at the root of the heap.
     *
     * <p> This operation takes {@code O(log(n))} time as the root and last indexed elements are first swapped before
     * the original root element is then removed. The {@code sink} method is then recursively called until the
     * <b>heap invariant</b> is met.
     *
     * @return the element at the root of the heap.
     */
    public T poll() {
        if (isEmpty()) throw new EmptyTreeException();

        int lastIndex = size - 1;
        T rootNode = heap.get(0);

        heap.set(0, heap.get(lastIndex));
        heap.set(lastIndex, null);
        size--;
        sink(0);
        return rootNode;
    }

    /**
     * The {@code peek} method returns the element at the root of the heap.
     *
     * <p> This operation takes {@code O(1)} time as the root of the heap is the first element in the heap list.
     *
     * @return the element at the root of the heap.
     */
    public T peek() {
        if (isEmpty()) return null;

        return heap.get(0);
    }

    /**
     * The {@code contains} method checks if an element exists in the heap.
     *
     * <p> This operation takes {@code O(n)} time as all branches of the tree may potentially need to be traversed to
     * figure out if the element exists within the heap.
     *
     * @return {@code true} if the element is in the heap, otherwise {@code false}.
     */
    public boolean contains(T t) {
        for (T node : heap) {
            if (node.equals(t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * The {@code size} method returns the current size of the heap.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the heap as it grows and
     * shrinks.
     *
     * @return the size of the heap.
     */
    public int size() {
        return size;
    }

    /**
     * The {@code isEmpty} method checks if the heap currently contains any elements or not.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the heap.
     *
     * @return {@code true} if the heap is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    /**
     * The {@code swim} method pushes an element up the roots of the tree <b>recursively</b> to ensure the
     * <b>heap invariant</b> is satisfied.
     *
     * <p> This operation takes {@code O(log(n))} time as the maximum number of times this method is executed is equal
     * to the height of the tree.
     *
     * @param index the index of the node in the tree which needs to swim.
     */
    private void swim(int index) {
        // If root node return
        if (index == 0) {
            return;
        }

        int parentIndex = (int) Math.ceil((double) index / 2) - 1;
        T parentNode = heap.get(parentIndex);
        T childNode = heap.get(index);

        // 1 = Greater Than, 0 = Equal, -1 = Less Than
        if (parentNode.compareTo(childNode) < 0) {

            // Swap nodes in tree
            heap.set(parentIndex, childNode);
            heap.set(index, parentNode);

            // Recursively call swim
            swim(parentIndex);
        }
    }

    /**
     * The {@code sink} method pushes an element down the roots of the tree <b>recursively</b> to ensure the
     * <b>heap invariant</b> is satisfied.
     *
     * <p> This operation takes {@code O(log(n))} time as the maximum number of times this method is executed is equal
     * to the height of the tree.
     *
     * @param index the index of the node in the tree which needs to sink.
     */
    private void sink(int index) {
        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = (index * 2) + 2;

        T parentNode = heap.get(index);
        T leftChildNode = null;
        T rightChildNode = null;

        // Check if children nodes exist
        if (leftChildIndex < size) {
            leftChildNode = heap.get(leftChildIndex);
        }

        if (rightChildIndex < size) {
            rightChildNode = heap.get(rightChildIndex);
        }

        // If no children, return
        if (leftChildNode == null && rightChildNode == null) {
            return;
        }

        int sinkChildIndex = -1;
        T childNode = null;

        if (
            leftChildNode != null &&
            rightChildNode != null &&
            parentNode.compareTo(leftChildNode) < 0 &&
            parentNode.compareTo(rightChildNode) < 0
        ) {
            if (leftChildNode.compareTo(rightChildNode) > 0) {
                childNode = leftChildNode;
                sinkChildIndex = leftChildIndex;
            } else {
                childNode = rightChildNode;
                sinkChildIndex = rightChildIndex;
            }
        } else if (leftChildNode != null && parentNode.compareTo(leftChildNode) < 0) {
            childNode = leftChildNode;
            sinkChildIndex = leftChildIndex;
        } else if (rightChildNode != null && parentNode.compareTo(rightChildNode) < 0) {
            childNode = rightChildNode;
            sinkChildIndex = rightChildIndex;
        }

        // Sink node
        if (sinkChildIndex > -1) {
            // Swap nodes in tree
            heap.set(sinkChildIndex, parentNode);
            heap.set(index, childNode);

            // Recursively call sink
            sink(sinkChildIndex);
        }
    }

    @Override
    public String toString() {
        return "BinaryHeap{" +
                "heap=" + heap +
                ", size=" + size +
                ", maxSize=" + maxSize +
                '}';
    }
}
