package dev.java10x.user.mapper;

import dev.java10x.user.dto.UserDto;
import dev.java10x.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserEntity toEntity (UserDto userDto);

    UserDto toDto (UserEntity userEntity);

}
