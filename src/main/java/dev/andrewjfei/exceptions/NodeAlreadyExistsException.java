package dev.andrewjfei.exceptions;

/**
 * If you want to restrict your data structure so that there are no repeated values. Then this exception should be
 * thrown when a node already exists in a tree or list.
 *
 * @author andrewjfei
 */
public class NodeAlreadyExistsException extends RuntimeException {

    public NodeAlreadyExistsException() {

    }
}
