package lt.Words.Configuration.Mapping;

import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MappingConfiguration {

    private List<Converter> customConverterList;

    @Autowired
    public MappingConfiguration(List<Converter> customConverterList) {
        this.customConverterList = customConverterList;
    }

    @Bean
    public Mapper mapper() {
        Mapper mapper = new Mapper();
        customConverterList.forEach(mapper::addConverter);
        return mapper;
    }
}
