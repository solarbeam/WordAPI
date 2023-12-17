package lt.Words.Configuration.Mapping;

import lt.Words.Dto.WordDto;
import lt.Words.Entity.Word;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordMapping {

    @Bean
    public Converter<WordDto, Word> wordDtoToWordConverter() {
        return new Converter<WordDto, Word>() {
            @Override
            public Word convert(MappingContext<WordDto, Word> mappingContext) {
                WordDto wordDto = mappingContext.getSource();
                Word word = new Word();
                word.setId(wordDto.getId());
                word.setValue(wordDto.getValue());
                word.setPalindrome(isPalindrome(wordDto.getValue()));
                return word;
            }
        };
    }

    private boolean isPalindrome(String value) {
        if (value == null) {
            return false;
        }
        String lowercaseValue = value.toLowerCase();
        String reverse = new StringBuilder(lowercaseValue).reverse().toString();
        return lowercaseValue.equals(reverse);
    }
}
