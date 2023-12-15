package lt.Words.Repository;

import lt.Words.Entity.Word;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordRepository implements Repository<Word, Long> {
    private final List<Word> mockDatabase;

    public WordRepository() {
        this.mockDatabase = new ArrayList<Word>();
    }

    public Word save(Word word) {
        Optional<Word> existingWord = mockDatabase.stream().filter(entity -> word.id.equals(entity.id)).findFirst();
        existingWord.ifPresentOrElse(entity -> {
            entity.value = word.value;
            entity.isPalindrome = word.isPalindrome;
        }, () -> mockDatabase.add(word));
        return existingWord.orElse(word);
    }

    public Optional<Word> findById(Long id) {
        return mockDatabase.stream().filter(entity -> entity.id.equals(id)).findFirst();
    }

    public Iterable<Word> findAll() {
        return mockDatabase;
    }

    public Word findByValue(String value) {
        return mockDatabase.stream().filter(entity -> entity.value.equals(value)).findFirst().orElse(null);
    }

    public void deleteById(Long id){
        mockDatabase.removeIf(entity -> entity.id.equals(id));
    }
}
