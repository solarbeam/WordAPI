package lt.Words.Exception.Word;

public class WordDoesNotExistException extends RuntimeException {

    public WordDoesNotExistException(String message) {
        super(message);
    }
}
