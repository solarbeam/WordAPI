package lt.Words.Controller;

import lt.Words.Configuration.Mapping.Mapper;
import lt.Words.Dto.WordDto;
import lt.Words.Entity.Word;
import lt.Words.Service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/word")
public class WordController {

    private Mapper mapper;
    private WordService wordService;

    @Autowired
    public WordController(Mapper modelMapper, WordService wordService) {
        this.mapper = modelMapper;
        this.wordService = wordService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WordDto> getWord(@PathVariable Long id) {
        return new ResponseEntity<WordDto>(
                mapper.map(wordService.findWordById(id), WordDto.class), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<WordDto>> getWords(@RequestParam(required = false) String value,
                                                  @RequestParam(required = false) boolean isPalindrome) {

        if (value != null) {
            List<WordDto> dummyList = new ArrayList<>();
            dummyList.add(mapper.map(wordService.findWordByValue(value), WordDto.class));
            return new ResponseEntity<List<WordDto>>(dummyList, HttpStatus.OK);
        }

        return new ResponseEntity<List<WordDto>>(
                mapper.mapList(isPalindrome ?
                        wordService.findWordsIsPalindrome() : wordService.findWords(), WordDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WordDto> createWord(@RequestBody WordDto wordDto) {
        return new ResponseEntity<WordDto>(
                mapper.map(wordService.addWord(mapper.map(wordDto, Word.class)), WordDto.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WordDto> updateWord(@RequestBody WordDto wordDto) {
        return new ResponseEntity<WordDto>(
                mapper.map(wordService.updateWord(mapper.map(wordDto, Word.class)), WordDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
    }
}
