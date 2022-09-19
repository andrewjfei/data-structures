package dev.andrewjfei.tree;

import dev.andrewjfei.exceptions.EmptyTreeException;
import dev.andrewjfei.exceptions.NodeAlreadyExistsException;

public class BinarySearchTree<T extends Comparable<T>> {

    private int size;
    private Node<T> root;

    public BinarySearchTree() {
        size = 0;
    }

    public void add(T t) {
        Node<T> newNode = new Node<>(t);

        if (isEmpty()) {
            root = newNode;
        } else {
            sink(root, newNode);
        }

        size++;
    }

    // TODO: Fix remove method (method is incorrect)
    public T remove(T t) {
        if (isEmpty()) throw new EmptyTreeException();

        Node<T> deletedNode = find(root, t);
        T deletedElement = deletedNode.getData();

        if (deletedNode != null) {
            // Check if deleted node has children
            if (deletedNode.getLeftChild() == null && deletedNode.getRightChild() == null) {
                setFromParent(deletedNode.getParent(), deletedNode, null);
            } else {
                Node<T> replacementNode;

                if (deletedNode.getLeftChild() != null) {
                    replacementNode = digRight(deletedNode.getLeftChild());

                    // Check if replacement node is direct child
                    if (!deletedNode.getLeftChild().equals(replacementNode)) {
                    }

                    sink(replacementNode, deletedNode.getRightChild());
                } else {
                    replacementNode = digLeft(deletedNode.getRightChild());

                    // Check if replacement node is direct child
                    if (!deletedNode.getRightChild().equals(replacementNode)) {
                        replacementNode.setLeftChild(replacementNode.getRightChild());
                        replacementNode.setRightChild(replacementNode.getParent());
                    }
                    replacementNode.setLeftChild(deletedNode.getLeftChild());
                }

                setFromParent(deletedNode.getParent(), deletedNode, replacementNode);
                setFromParent(replacementNode.getParent(), replacementNode, null);
            }

            size--;
        }

        return deletedElement;
    }

    public T peek() {
        if (isEmpty()) return null;

        return root.getData();
    }

    public boolean contains(T t) {
        return find(root, t) != null ? true : false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void sink(Node<T> parentNode, Node<T> newNode) {
        if (newNode == null) {
            return;
        }

        if (parentNode.getData().compareTo(newNode.getData()) > 0) {
            // If parent left child is null add node, otherwise keep sinking
            if (parentNode.getLeftChild() == null) {
                newNode.setParent(parentNode);
                parentNode.setLeftChild(newNode);
            } else {
                sink(parentNode.getLeftChild(), newNode);
            }
        } else if (parentNode.getData().compareTo(newNode.getData()) < 0) {
            // If parent right child is null add node, otherwise keep sinking
            if (parentNode.getRightChild() == null) {
                newNode.setParent(parentNode);
                parentNode.setRightChild(newNode);
            } else {
                sink(parentNode.getRightChild(), newNode);
            }
        } else {
            throw new NodeAlreadyExistsException();
        }
    }

    private Node<T> find(Node<T> node, T target) {
        if (node.getData().compareTo(target) > 0) {
            // If parent left child is null return null otherwise keep finding
            if (node.getLeftChild() == null) {
                return null;
            } else {
                return find(node.getLeftChild(), target);
            }
        } else if (node.getData().compareTo(target) < 0) {
            // If parent right child is null return null otherwise keep finding
            if (node.getRightChild() == null) {
                return null;
            } else {
                return find(node.getRightChild(), target);
            }
        } else {
            return node;
        }
    }

    private Node<T> digLeft(Node<T> node) {
        if (node.getLeftChild() == null) {
            return node;
        }

        return digLeft(node.getLeftChild());
    }

    private Node<T> digRight(Node<T> node) {
        if (node.getRightChild() == null) {
            return node;
        }

        return digRight(node.getRightChild());
    }

    private void setFromParent(Node<T> parentNode, Node<T> deletedNode, Node<T> replacementNode) {
        if (replacementNode != null) {
            replacementNode.setParent(parentNode);
        }

        if (parentNode.getLeftChild().equals(deletedNode)) {
            parentNode.setLeftChild(replacementNode);
        } else {
            parentNode.setRightChild(replacementNode);
        }
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "size=" + size +
                ", root=" + root +
                '}';
    }

    private class Node<T> {
        private T data;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }
}
