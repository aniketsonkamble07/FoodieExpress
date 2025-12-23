package com.foodieexpress.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class CategoryAlreadyPresentException  extends RuntimeException{
    public CategoryAlreadyPresentException(String message)
    {
        super(message);
    }
}
