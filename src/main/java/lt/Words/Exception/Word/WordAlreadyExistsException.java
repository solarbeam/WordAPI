package lt.Words.Exception.Word;

public class WordAlreadyExistsException extends RuntimeException {

    public WordAlreadyExistsException(String message) {
        super(message);
    }
}
