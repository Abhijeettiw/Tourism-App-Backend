package com.tourism.tourism.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
public class InvalidException extends RuntimeException{
    private String error;
    private HttpStatus status;
    public InvalidException(String error, HttpStatus status)
    {
        this.error=error;
        this.status=status;
    }
}
