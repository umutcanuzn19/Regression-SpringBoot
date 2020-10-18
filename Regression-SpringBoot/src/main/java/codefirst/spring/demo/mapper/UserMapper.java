package codefirst.spring.demo.mapper;

import codefirst.spring.demo.entity.User;

import codefirst.spring.demo.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}
