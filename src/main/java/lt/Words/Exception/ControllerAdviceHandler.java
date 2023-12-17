package lt.Words.Exception;

import lt.Words.Exception.Word.WordAlreadyExistsException;
import lt.Words.Exception.Word.WordDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler({WordAlreadyExistsException.class})
    public ResponseEntity<Object> handleWordAlreadyExistsException(WordAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({WordDoesNotExistException.class})
    public ResponseEntity<Object> handleWordDoesNotExistException(WordDoesNotExistException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
