package com.onlinetaskmanagementsystem.OTMS.Advice;


import com.onlinetaskmanagementsystem.OTMS.Exception.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class BasicValidation {
    static final String EXCEPTION = "Exception";

    static final String ERRMESSAGE = "Err Message";


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMsg = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    errorMsg.put(error.getField(), error.getDefaultMessage());
                });

        return errorMsg;
    }
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(UserCreationException.class)
    public Map<String,String> userCreationException(UserCreationException error){
        Map<String,String> errObj=new HashMap<>();
        errObj.put("ERROR:",error.getMessage());
        return errObj;
    }



}
