package io.agileIntelligence.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CostumResponceEntityExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(Exception ex, WebRequest request){
        ProjectIdExceptionResponce exceptionResponce = new ProjectIdExceptionResponce(ex.getMessage());
        return new ResponseEntity(exceptionResponce, HttpStatus.BAD_REQUEST);
    }
}
