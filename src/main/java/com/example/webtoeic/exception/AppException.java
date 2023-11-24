package com.example.webtoeic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppException extends RuntimeException{
    public AppException() {
        super();
    }
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
    public AppException(String message) {
        super(message);
    }
    public AppException(Throwable cause) {
        super(cause);
    }
}
