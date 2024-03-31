package steammachinist.vacationpaycalculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(UnsatisfiedServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMissingParams(MethodArgumentTypeMismatchException ex) {
        String errorMessage = ex.getName() + " parameter has wrong type or format";
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
