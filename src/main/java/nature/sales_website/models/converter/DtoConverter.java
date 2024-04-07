package nature.sales_website.models.converter;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoConverter {
    private static ModelMapper modelMapper = new ModelMapper();
    // in which, T is the input object type and D is the output object type
    public static <T, D> D convertToDto(T entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    public static <T, D> List<D> convertToDtoList(List<T> entityList, Class<D> dtoClass){
        return entityList.stream().map(entity -> modelMapper.map(entity, dtoClass)).collect(Collectors.toList());
    }

    public static <T, D> Set<D> convertToDtoSet(List<T> entityList, Class<D> dtoClass){
        return entityList.stream().map(entity -> modelMapper.map(entity, dtoClass)).collect(Collectors.toSet());
    }
}
