package io.personalprojects.sked.exceptions;

/**
 * A generic sked exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class SkedException extends Exception {

    /**
     * @see Exception#Exception(String)
     */
    public SkedException(String message) {
        super(message);
    }
}
