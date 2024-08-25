package nature.sales_website.models.converter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    public static <T, D> Set<D> convertToDtoSet(Set<T> entityList, Class<D> dtoClass){
        return entityList.stream().map(entity -> modelMapper.map(entity, dtoClass)).collect(Collectors.toSet());
    }

    public static <T, D> Page<D> convertToDtoPage(Page<T> entityPage, Class<D> dtoClass, Pageable pageable){
        List<D> dtoList = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }
}
