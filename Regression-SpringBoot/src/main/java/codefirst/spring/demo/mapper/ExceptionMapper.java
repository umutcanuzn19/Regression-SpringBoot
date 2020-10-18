package codefirst.spring.demo.mapper;

import codefirst.spring.demo.dto.ExceptionDTO;
import codefirst.spring.demo.exception.CodefirstException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ExceptionMapper {
    ExceptionDTO toExceptionDTO(CodefirstException denemeException);
}
