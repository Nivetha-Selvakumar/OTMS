package com.onlinetaskmanagementsystem.otms.advice;


import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCredentialException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    static final String EXCEPTION = "Exception";

    static final String ERRMESSAGE = "Error Message";


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMsg = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error->errorMsg.put(error.getField(), error.getDefaultMessage()));
        return errorMsg;
    }
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(UserCreationException.class)
    public Map<String,String> userCreationException(UserCreationException error){
        Map<String,String> errObj=new HashMap<>();
        errObj.put("ERROR:",error.getMessage());
        return errObj;
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserCredentialException.class)
    public Map<String,String> userCredentialException(UserCredentialException error){
        Map<String,String> errObj=new HashMap<>();
        errObj.put("ERROR:",error.getMessage());
        return errObj;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> userNotFoundException(UserNotFoundException error){
        Map<String,String> errObj=new HashMap<>();
        errObj.put("ERROR:",error.getMessage());
        return errObj;
    }

}
