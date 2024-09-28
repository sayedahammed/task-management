package com.task.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDetails {

    private String message;
    private HttpStatus status;

    public ErrorDetails(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
