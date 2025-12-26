package com.foodieexpress.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class MenuItemAlreadyPresentException  extends  RuntimeException{
    public  MenuItemAlreadyPresentException(String message)
    {
        super(message);
    }
}
