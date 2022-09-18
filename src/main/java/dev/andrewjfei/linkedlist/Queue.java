package dev.andrewjfei.linkedlist;

import dev.andrewjfei.exceptions.EmptyListException;

/**
 * A {@code Queue} is a list data structure which follows the FIFO (First In First Out) principle. This means that
 * insertions are only allowed at the end of the list while deletions are only allowed at the start of the list.
 *
 * <p> A standard {@code Queue} just requires a singly linked list as the {@code Queue} only needs to traverse to the
 * next {@code Node}.
 *
 * <p> This data structure can be thought of as a real world queue, where each element added to the list is another
 * person which has lined up at the back of the queue. Only the person at the front will be removed from the queue if
 * an opening occurs.
 *
 * @author andrewjfei
 *
 * @param <T> the data type of the list.
 */
public class Queue<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public Queue() {
        size = 0;
    }

    /**
     * The <b>enqueue</b> method adds an element to the back of the queue.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the back of the
     * queue.
     *
     * @param t the element which is being added to the back of the queue.
     */
    public void enqueue(T t) {
        Node<T> addedNode = new Node(t);

        if (isEmpty()) {
            head = addedNode;
        } else {
            tail.setNext(addedNode);
        }

        tail = addedNode;
        size++;
    }

    /**
     * The <b>dequeue</b> method removes the element at the start of the queue.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the start of the
     * queue. Each node also has a pointer pointing to the next node.
     *
     * @return the element at the start of the queue.
     */
    public T dequeue() {
        if (isEmpty()) throw new EmptyListException();

        Node<T> deletedNode = head; // 1 -> 2 -> 3 -> 4 -> 5

        head = head.getNext();
        size--;

        return deletedNode.getData();
    }

    /**
     * The <b>peek</b> method returns the element at the start of the queue.
     *
     * <p> This operation takes {@code O(1)} time as there is already a pointer pointing to the node at the start of the
     * queue.
     *
     * @return the element at the top of the stack.
     */
    public T peek() {
        if (isEmpty()) throw new EmptyListException();

        return head.getData();
    }

    /**
     * The <b>contains</b> method checks if an element exists in the queue.
     *
     * <p> This operation takes {@code O(n)} time as the element could be the last element of the queue, hence
     * the entire queue would need to be traversed in order to know whether the element is in the queue or not.
     *
     * @return {@code true} if the element is in the queue, otherwise {@code false}.
     */
    public boolean contains(T t) {
        Node<T> copy = head;

        while (copy != null) {
            if (copy.getData().equals(t)) {
                return true;
            }

            copy = copy.getNext();
        }

        return false;
    }

    /**
     * The <b>size</b> method returns the current size of the queue.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the queue as it grows and
     * shrinks.
     *
     * @return the size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * The <b>isEmpty</b> method checks if the queue currently contains any elements or not.
     *
     * <p> This operation takes {@code O(1)} time as there is a variable tracking the size of the queue. Also, if the
     * tail pointer is pointing to {@code null} then it is another indicator that the queue is empty.
     *
     * @return {@code true} if the queue is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    /**
     * A {@code Node} is a crucial building block of a linked list data structure, which is the basis of the
     * {@code Queue}.
     *
     * @see Queue
     *
     * @param <T> the data type of the node.
     */
    public class Node<T> {
        private T data;
        private Node next;

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
         * Retrieves the next node in the list.
         *
         * @return the next node in the list.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the next pointer of the current node to provided node.
         *
         * @param next the previous node in the list.
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }
}
