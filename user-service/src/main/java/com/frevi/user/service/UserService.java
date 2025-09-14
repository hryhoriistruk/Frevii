package com.frevi.user.service;

import com.frevi.user.dto.UserDto;
import com.frevi.user.entity.UserJpaEntity;
import com.frevi.user.mapper.UserMapper;
import com.frevi.user.repository.UserJpaRepository;
import com.frevi.user.request.UserRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final MinioService minioService;
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    private UserDto getUserDto(UserJpaEntity userJpaEntity) throws Exception {
        UserDto userDto = userMapper.toDto(userJpaEntity);

        userDto.setAvatarUrl(minioService.downloadFile(userJpaEntity.getAvatarFileName()));

        return userDto;
    }

    public UserDto save(UserRequest request) throws Exception {
        minioService.uploadFile(
                request.avatar().getName(),
                request.avatar().getInputStream(),
                request.avatar().getSize(),
                request.avatar().getContentType()
        );

        UserJpaEntity user = userJpaRepository.save(userMapper.toJpaEntity(request));

        return getUserDto(user);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDto findById(long id) throws Exception {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return getUserDto(userJpaEntity);
    }

    @SneakyThrows
    public Page<UserDto> findAll(Pageable pageable) {
        return userJpaRepository
                    .findAll(pageable)
                    .map(this::getUserDto);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteById(long id) throws EntityNotFoundException{
        if (!userJpaRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        userJpaRepository.deleteById(id);
    }
}
