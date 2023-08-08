package org.adam.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public final String key;

    public ObjectNotFoundException(String key) {
        this.key = key;
    }
}
