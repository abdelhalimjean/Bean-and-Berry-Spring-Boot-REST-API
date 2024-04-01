package mr.springbootapi.exception;

import mr.springbootapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleItemNotFoundException(ItemNotFoundException ex) {
        // Create an ApiResponse with the exception message and status code
        ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        // Return the ApiResponse with 404 NOT FOUND status
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidPriceException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleInvalidPriceException(InvalidPriceException ex) {
        // Create an ApiResponse with the exception message and status code
        ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        // Return the ApiResponse with 400 BAD REQUEST status
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleGenericException(GenericException ex) {
        // Create an ApiResponse with the exception message and status code
        ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        // Return the ApiResponse with 500 INTERNAL SERVER ERROR status
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        // Create an ApiResponse with a generic error message and status code
        ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred: " + ex.getMessage(), null);
        // Return the ApiResponse with 500 INTERNAL SERVER ERROR status
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
