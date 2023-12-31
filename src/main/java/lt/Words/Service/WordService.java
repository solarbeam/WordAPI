package lt.Words.Service;

import lt.Words.Entity.Word;
import lt.Words.Exception.Word.WordAlreadyExistsException;
import lt.Words.Exception.Word.WordDoesNotExistException;
import lt.Words.Repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word findWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(() -> new WordDoesNotExistException("Word does not exist"));
    }

    public Word findWordByValue(String value) {
        return wordRepository.findByValue(value).orElseThrow(() -> new WordDoesNotExistException("Word does not exist"));
    }

    public List<Word> findWords() {
        return wordRepository.findAll();
    }

    public List<Word> findWordsIsPalindrome() {
        return wordRepository.findAllByIsPalindrome();
    }

    public Word addWord(Word word) {
        if(word.getId() != null && wordRepository.findById(word.getId()).isPresent()) {
            throw new WordAlreadyExistsException("Word with a given id already exists, use PUT to update");
        }
        return wordRepository.save(word);
    }

    public Word updateWord(Word word) {
        if(word.getId() != null) {
            Optional<Word> existingWord = wordRepository.findById(word.getId());
            existingWord.ifPresentOrElse(
                    entity -> {
                        // only save properties that are not null
                        word.setValue(word.getValue() != null ? word.getValue() : entity.getValue());
                    },
                    () -> {throw new WordDoesNotExistException("Word doesn't exist, use POST to create");});
        } else {
            throw new WordDoesNotExistException("Word doesn't exist, use POST to create");
        }
        return wordRepository.save(word);
    }

    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

}
