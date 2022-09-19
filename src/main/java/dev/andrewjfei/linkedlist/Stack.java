package dev.andrewjfei.linkedlist;

import dev.andrewjfei.exceptions.EmptyListException;

/**
 * A {@code Stack} is a list data structure which follows the LIFO (Last In First Out) principle. This means that
 * insertions and deletions are only allowed at the end of the list.
 *
 * <p> A standard {@code Stack} just requires a singly linked list as the {@code Stack} only needs to traverse to the
 * previous {@code Node}.
 *
 * <p> This data structure can be thought of a pile of plates, where each element added to the list is another "plate"
 * which is added to the pile. In order to get to the first plate at the bottom of the pile, each plate starting from
 * the top of the pile will need to be removed one by one until the last plate is reached.
 *
 * @author andrewjfei
 *
 * @param <T> the data type of the list.
 *
 */
public class Stack<T> {
    private int size;
    private Node<T> tail;

    public Stack() {
        size = 0;
    }

    /**
     * The {@code push} method adds an element to the top of the stack.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the top of the
     * stack.
     *
     * @param t the element which is being added to the top of the stack.
     */
    public void push(T t) {
        Node<T> addedNode = new Node(t);

        if (!isEmpty()) {
            addedNode.setPrev(tail);
        }

        tail = addedNode;
        size++;
    }

    /**
     * The {@code pop} method removes the element at the top of the stack.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the top of the
     * stack. Each node also has a pointer pointing to the previous node.
     *
     * @return the element at the top of the stack.
     */
    public T pop() {
        if (isEmpty()) throw new EmptyListException();

        Node<T> deletedNode = tail;

        tail = tail.getPrev();
        size--;

        return deletedNode.getData();
    }

    /**
     * The {@code peek} method returns the element at the top of the stack.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the top of the
     * stack.
     *
     * @return the element at the top of the stack.
     */
    public T peek() {
        if (isEmpty()) return null;

        return tail.getData();
    }

    /**
     * The {@code contains} method checks if an element exists in the stack.
     *
     * <p> This operation takes {@code O(n)} time as the element could be the first element of the stack, hence
     * all elements above it would need to be removed in order to know whether the element is in the stack or not.
     *
     * @return {@code true} if the element is in the stack, otherwise {@code false}.
     */
    public boolean contains(T t) {
        Node<T> copy = tail;

        while (copy != null) {
            if (copy.getData().equals(t)) {
                return true;
            }

            copy = copy.getPrev();
        }

        return false;
    }

    /**
     * The {@code size} method returns the current size of the stack.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the stack as it grows and
     * shrinks.
     *
     * @return the size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * The {@code isEmpty}> method checks if the stack currently contains any elements or not.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the stack. Also, if the
     * tail pointer is pointing to {@code null} then it is another indicator that the stack is empty.
     *
     * @return {@code true} if the stack is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    /**
     * A {@code Node} is a crucial building block of a linked list data structure, which is the basis of the
     * {@code Stack}.
     *
     * @see Stack
     *
     * @param <T> the data type of the node.
     */
    private class Node<T> {
        private T data;
        private Node prev;

        public Node(T data) {
            this.data = data;
        }

        /**
         * Retrieves the data stored in the current node.
         *
         * @return the data stored in the node.
         */
        public T getData() {
            return data;
        }

        /**
         * Retrieves the previous node in the list.
         *
         * @return the previous node in the list.
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Set the previous pointer of the current node to provided node.
         *
         * @param prev the previous node in the list.
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
