package lt.Words.Configuration.Mapping;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper extends ModelMapper {

    public <S, D> List<D> mapList(List<S> source, Class<D> destinationClass) {
        return source.stream().map(element -> this.map(element, destinationClass)).collect(Collectors.toList());
    }
}
