package net.summer23project.wtebackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Liyang
 */
@Getter
@AllArgsConstructor
public class APIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
