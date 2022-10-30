package com.viettel.account.service.exception_handler;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@ControllerAdvice
@Tag(name = "Handler for exceptions",
        description = "All exception will be catched here before take next steps")
@Log4j2
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String catchNotFound(Exception e) {
        log.error(e.getMessage());
        return "Not-Found";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public String catchNullPointerEx(Exception exception) {
        log.error(exception.getMessage());
        return "NullPointerException";
    }
}
