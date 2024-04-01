package mr.springbootapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
/*
 This annotation marks a class as a global exception handler.
 Methods within this class will be executed whenever exceptions are thrown by any controller.
*/
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class) //It means that this method will be called to handle when ItemNotFoundException is thrown
    @ResponseBody
    public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPriceException.class)
    @ResponseBody
    public ResponseEntity<String> handleInvalidPriceException(InvalidPriceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(GenericException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
