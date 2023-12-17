package lt.Words.Repository;

import lt.Words.Entity.Word;
import lt.Words.Exception.Word.WordDoesNotExistException;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class WordRepository implements Repository<Word, Long> {
    private final List<Word> mockDatabase;
    private Long idSequence = 1l;

    public WordRepository() {
        this.mockDatabase = new ArrayList<Word>();
    }

    public Word save(Word word) {
        if (word.getId() == null) {
            word.setId(idSequence++);
        }
        Optional<Word> existingWord = mockDatabase.stream().filter(entity -> word.getId().equals(entity.getId())).findFirst();
        existingWord.ifPresentOrElse(entity -> {
            entity.setValue(word.getValue());
            entity.setPalindrome(word.isPalindrome());
        }, () -> mockDatabase.add(word));
        return existingWord.orElse(word);
    }

    public Optional<Word> findById(Long id) {
        return mockDatabase.stream().filter(entity -> entity.getId().equals(id)).findFirst();
    }

    public List<Word> findAll() {
        return mockDatabase;
    }

    public List<Word> findAllByIsPalindrome() {
        return mockDatabase.stream().filter(Word::isPalindrome).collect(Collectors.toList());
    }

    public Optional<Word> findByValue(String value) {
        return mockDatabase.stream().filter(entity -> entity.getValue().equals(value)).findFirst();
    }

    public void deleteById(Long id){
        if(!mockDatabase.removeIf(entity -> entity.getId().equals(id))) {
            throw new WordDoesNotExistException("Word does not exist");
        }
    }
}
