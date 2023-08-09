package net.summer23project.wtebackend.exception;

/**
 * @author Liyang
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
