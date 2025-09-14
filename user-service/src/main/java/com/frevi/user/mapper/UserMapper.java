package com.frevi.user.mapper;

import com.frevi.user.dto.UserDto;
import com.frevi.user.entity.UserJpaEntity;
import com.frevi.user.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "avatar", source = "avatarFileName", qualifiedByName = "mapAvatar")
    public abstract UserJpaEntity toJpaEntity(UserRequest request);

    public abstract UserDto toDto(UserJpaEntity entity);

    @Named("mapAvatar")
    public String mapAvatar(MultipartFile avatar) {
        return avatar.getName();
    }
}
