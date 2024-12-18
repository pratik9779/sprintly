package com.sprintly.sprintly.exception;

import com.sprintly.sprintly.exception.custom.CustomException;
import com.sprintly.sprintly.exception.custom.UserAlreadyPresent;
import com.sprintly.sprintly.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyPresent.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse userAlreadyFound(UserAlreadyPresent exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse customException(CustomException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
