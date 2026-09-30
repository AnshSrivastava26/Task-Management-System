package org.TMS.Exception;

public class DuplicateTaskException extends RuntimeException {

    public DuplicateTaskException(String message) {
        super(message);
    }
}