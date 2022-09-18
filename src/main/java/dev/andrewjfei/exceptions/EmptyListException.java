package dev.andrewjfei.exceptions;

/**
 * This exception should be thrown when a list is empty when trying to perform actions where elements are required.
 *
 * @author andrewjfei
 */
public class EmptyListException extends RuntimeException {

    public EmptyListException() {

    }
}
