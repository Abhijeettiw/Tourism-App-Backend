package com.tourism.tourism.Exception;

import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.lang.String;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException rex)
    {
        String message= rex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleArgsNotValidException(MethodArgumentNotValidException mex)
    {
        Map<String,String> errors=new HashMap<String,String>();
        mex.getBindingResult().getAllErrors().forEach((error)->
        {
            String fieldName = ((FieldError)error).getField();
            String defaultMessage=error.getDefaultMessage();
             errors.put(fieldName,defaultMessage);
        });
        return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<List<ArrayList>> handleInvalidException(InvalidException in)
    {
        ArrayList error=new ArrayList<>();
        String err=in.getError();
        String message=in.getMessage();
        HttpStatus status=in.getStatus();
        error.add(err);
        error.add(message);
        error.add(status);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
